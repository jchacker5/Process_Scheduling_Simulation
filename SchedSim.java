
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SchedSim {
    private ExternalEvents externalEvents;
    private CPU cpu;
    private ReadyQueue readyQueue;
    private WaitingQueue waitingQueue;

    public SchedSim() {
        externalEvents = new ExternalEvents();
        cpu = new CPU();
        readyQueue = new ReadyQueue();
        waitingQueue = new WaitingQueue();
    }

    public void startSimulation(List<String[]> commands) {
        int currentTime = 0;
        int commandIndex = 0;
        
        while (true) {
            // Process external events
            processExternalEvents(commands, commandIndex, currentTime);
            
            // Process internal events
            processInternalEvents();
            
            // Execute CPU cycle
            execute();
            
            // Increment current time
            currentTime++;

            // Termination condition: when no more commands and queues are empty
            if (commandIndex >= commands.size() && readyQueue.isEmpty() && waitingQueue.isEmpty() && cpu.isEmpty()) {
                break;
            }
        }
    }

    public void processExternalEvents(List<String[]> commands, int commandIndex, int currentTime) {
        while (commandIndex < commands.size() && Integer.parseInt(commands.get(commandIndex)[0]) == currentTime) {
            String[] commandParts = commands.get(commandIndex);
            String command = commandParts[1];
            switch (command) {
                case "J":
                    Job job = new Job(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[3]));
                    readyQueue.addJob(job);
                    break;
                case "W":
                    if (!cpu.isEmpty()) {
                        waitingQueue.addJob(cpu.removeJob());
                    }
                    break;
                case "R":
                    Job waitingJob = waitingQueue.getJob(Integer.parseInt(commandParts[2]));
                    if (waitingJob != null) {
                        readyQueue.addJob(waitingJob);
                    }
                    break;
                case "C":
                    if (!cpu.isEmpty()) {
                        cpu.removeJob();
                    }
                    break;
                case "T":
                    readyQueue.removeJob(Integer.parseInt(commandParts[2]));
                    waitingQueue.removeJob(Integer.parseInt(commandParts[2]));
                    break;
            }
            commandIndex++;
        }
    }

    public void processInternalEvents() {
        if (cpu.isEmpty() && !readyQueue.isEmpty()) {
            cpu.setJob(readyQueue.pollJob());
        }
    }

    public void execute() {
        if (!cpu.isEmpty()) {
            Job currentJob = cpu.getJob();
            currentJob.execute();
        }
    }

    public static void main(String[] args) {
        List<String[]> commands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                commands.add(line.split("\s+"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SchedSim schedSim = new SchedSim();
        schedSim.startSimulation(commands);
    }
}
