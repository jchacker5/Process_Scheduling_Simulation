import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class Scheduler {
    private PriorityQueue<Job> readyQueue;
    private Queue<Job> waitQueue;
    private Job currentJob;
    private int time;
    private int jobCounter;

    // Initialize the scheduler
    public Scheduler() {
        this.readyQueue = new PriorityQueue<>((a, b) -> a.getPriority() - b.getPriority());
        this.waitQueue = new LinkedList<>();
        this.currentJob = null;
        this.time = 0;
        this.jobCounter = 0;
    }

    // Handle each command
    public void handleCommand(String commandLine) {
        // Parse command and arguments
        String[] parts = commandLine.split(" ");
        String command = parts[1];

        // Implement each command logic
        switch (command) {
            case "J":
                createJob(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                break;
            case "W":
                startIO();
                break;
            case "R":
                completeIO(Integer.parseInt(parts[2]));
                break;
            case "C":
                completeCurrentJob();
                break;
            case "T":
                terminateJob(Integer.parseInt(parts[2]));
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    // Create a new job and add it to the ready queue
    public void createJob(int priority, int timeRemaining) {
        Job newJob = new Job(++jobCounter, priority, timeRemaining);
        readyQueue.add(newJob);
        // Implement logic to print a message or move to CPU if required
    }

    // Start I/O for the current job
    public void startIO() {
        if (currentJob != null) {
            waitQueue.add(currentJob);
            currentJob = null;
            // Implement logic to print a message or move another job to CPU
        }
    }

    // Complete I/O for a waiting job
    public void completeIO(int jobNumber) {
        // Implement logic to move the job from waitQueue to readyQueue
    }

    // Complete the current job
    public void completeCurrentJob() {
        if (currentJob != null) {
            // Implement logic to remove the job and print a message
            currentJob = null;
        }
    }

    // Terminate a job
    public void terminateJob(int jobNumber) {
        // Implement logic to remove the job from either queue and print a message
    }

    // Main simulation loop
    public void runSimulation() {
        while (true) {
            // Implement logic to process each tick
        }
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        // Implement logic to read commands and call handleCommand
        scheduler.runSimulation();
    }
}
