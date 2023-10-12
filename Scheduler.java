import java.util.*;
import java.io.*;

public class Scheduler {
    // Priority queue for ready jobs, sorted by priority
    private PriorityQueue<Job> readyQueue;

    // Queue for jobs waiting for disk I/O
    private Queue<Job> waitQueue;

    // The job currently executing on the CPU
    private Job currentJob;

    // Current time in the simulation
    private int currentTime;

    // Job counter for assigning unique job numbers
    private int jobCounter;

    // Statistical variables
    private int completedJobs;
    private int terminatedJobs;
    private int totalWaitTime;
    private int totalTurnaroundTime;

    public Scheduler() {
        // Initialize variables
        this.readyQueue = new PriorityQueue<>((a, b) -> a.getPriority() - b.getPriority());
        this.waitQueue = new LinkedList<>();
        this.currentJob = null;
        this.currentTime = 0;
        this.jobCounter = 0;
        this.completedJobs = 0;
        this.terminatedJobs = 0;
        this.totalWaitTime = 0;
        this.totalTurnaroundTime = 0;
    }

    // Handle commands received from the file
    public void handleCommand(String[] commandParts) {
        String command = commandParts[1];
        switch (command) {
            case "J":
                createJob(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[3]));
                break;
            case "W":
                startIO();
                break;
            case "R":
                completeIO(Integer.parseInt(commandParts[2]));
                break;
            case "C":
                completeCurrentJob();
                break;
            case "T":
                terminateJob(Integer.parseInt(commandParts[2]));
                break;
        }
    }

    // Create a new job and add it to the ready queue
    public void createJob(int priority, int timeRemaining) {
        Job newJob = new Job(++jobCounter, priority, timeRemaining);
        readyQueue.add(newJob);
        System.out.println("Spawning new job to the ready queue: ");
        System.out.println("---Job #" + newJob.getJobNumber() + ", priority = " + newJob.getPriority() + ", time remaining = " + newJob.getTimeRemaining());
    }

    // Move the current job to the wait queue for I/O
    public void startIO() {
        if (currentJob != null) {
            waitQueue.add(currentJob);
            currentJob = null;
            System.out.println("Start I/O for current job:");
        }
    }

    // Move a job from the wait queue to the ready queue after I/O completion
    public void completeIO(int jobNumber) {
        Job job = null;
        for (Job j : waitQueue) {
            if (j.getJobNumber() == jobNumber) {
                job = j;
                break;
            }
        }
        if (job != null) {
            waitQueue.remove(job);
            readyQueue.add(job);
            System.out.println("Make waiting Job #" + jobNumber + " ready.");
        } else {
            System.out.println("Failed: Job #" + jobNumber + " is not waiting.");
        }
    }

    // Complete the current job and remove it from the system
    public void completeCurrentJob() {
        if (currentJob != null) {
            System.out.println("Trying to complete the current running job:");
            currentJob.setTotalTime(currentTime);
            totalTurnaroundTime += currentJob.getTotalTime();
            completedJobs++;
            System.out.println("---Exit Type: Completed.");
            System.out.println("---Job #" + currentJob.getJobNumber() + ", priority = " + currentJob.getPriority() + ", time remaining = " + currentJob.getTimeRemaining());
            System.out.println("---Running Time = " + (currentJob.getInitialTime() - currentJob.getTimeRemaining()) + ", Idle Time = " + (currentJob.getTotalTime() - currentJob.getInitialTime()) + ", Turn-Around Time = " + currentJob.getTotalTime() + ", Entered CPU " + currentJob.getTimesEnteredCPU() + " time(s).");
            currentJob = null;
        }
    }

    // Terminate a job based on its job number
    public void terminateJob(int jobNumber) {
        boolean removed = readyQueue.removeIf(job -> job.getJobNumber() == jobNumber);
        if (!removed) {
            removed = waitQueue.removeIf(job -> job.getJobNumber() == jobNumber);
        }
        if (removed) {
            terminatedJobs++;
            System.out.println("Trying to terminate job #" + jobNumber + ":");
            System.out.println("---Success: Job #" + jobNumber + " terminated.");
        } else {
            System.out.println("Failed: Cannot terminate running job or job not found.");
        }
    }

    // Main simulation loop
    public void runSimulation(List<String[]> commands) {
        int commandIndex = 0;
        while (true) {
            // Handle external events
            while (commandIndex < commands.size() && Integer.parseInt(commands.get(commandIndex)[0]) == currentTime) {
                handleCommand(commands.get(commandIndex));
                commandIndex++;
            }

            // Handle internal events (Time slice)
            if (currentJob != null) {
                currentJob.setTimeRemaining(currentJob.getTimeRemaining() - 1);
                if (currentJob.getTimeRemaining() == 0) {
                    completeCurrentJob();
                }
            }

            // Assign CPU to the next job in the ready queue
            if (currentJob == null && !readyQueue.isEmpty()) {
                currentJob = readyQueue.poll();
                currentJob.setTimesEnteredCPU(currentJob.getTimesEnteredCPU() + 1);
                System.out.println("Job #" + currentJob.getJobNumber() + " gets the CPU.");
            }

            // End of simulation condition
            if (commandIndex >= commands.size() && currentJob == null && readyQueue.isEmpty() && waitQueue.isEmpty()) {
                break;
            }

            currentTime++;
        }

        // Final Statistics
        System.out.println("Final Statistics:");
        System.out.println("---Number of jobs entering the system: " + jobCounter);
        System.out.println("---Number of jobs terminated: " + terminatedJobs);
        System.out.println("---Number of completed jobs: " + completedJobs);
        System.out.println("---Average wait time (completed jobs only): " + (totalWaitTime / (double) completedJobs));
        System.out.println("---Average turn-around time (completed jobs only): " + (totalTurnaroundTime / (double) completedJobs));
    }

    // Entry point
    public static void main(String[] args) {
        List<String[]> commands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            // Skip the first line that says "INPUT Data File:"
            br.readLine();
            while ((line = br.readLine()) != null && !line.equals("0 end of file")) {
                commands.add(line.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scheduler scheduler = new Scheduler();
        scheduler.runSimulation(commands);
    }
}
