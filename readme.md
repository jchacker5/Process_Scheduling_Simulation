# Process Scheduling Simulation Program
## Developer Information
**Name**: Joe Defendre  
**Course Section**: Comp 350-003

## Overview
This Java program simulates a preemptive priority-based round-robin scheduling algorithm for a simple operating system. The program considers two resources, a CPU and a disk, and operates on an undetermined number of jobs. Jobs have attributes like job number, priority, and maximum time remaining. The simulation handles five different commands to manipulate the state of jobs in the system: Create (J), Start I/O (W), Complete I/O (R), Complete current job (C), and Terminate job (T).

## How to Use
1. **Compile the Code**: Compile all the Java files in the project directory.
2. **Run the Program**: Run `SchedSim.java`.
3. **Input Commands**: The program will read commands from an `input.txt` file to simulate events like creating a new job, starting I/O, etc. Make sure to place the `input.txt` in the same directory as your Java files.
    - Format in `input.txt`: `TIME COMMAND [ARGUMENTS]`
    - Example in `input.txt`: `1 J 3 8`
4. **End Simulation**: The simulation ends when it reaches "0 end of file" in `input.txt`.
5. **View Results**: The program will display events as they are processed and also output summary statistics at the end.

### Command List
- `J priority time_estimate`: Creates a new job with the given priority and time estimate.
- `W`: Starts I/O for the current job.
- `R job_number`: Completes I/O for the specified job.
- `C`: Completes the current job.
- `T job_number`: Terminates the specified job.

## For Non-Technical Users
Don't worry about the technical details; just follow the steps under "How to Use". The program will guide you through the simulation.

## Incomplete or Non-Working Features
All project requirements have been implemented. However, the simulation assumes that the input commands are valid and in ascending order of time.

## Files included for the Project:

1. `Job.java` - This class stores all the job related information such as job number, priority, timeRemaining etc. and also maintains the running time, idle time, cpu entries etc.
2. `CPU.java` - This class holds the currently running job.
3. `ReadyQueue.java` - This class implements a Priority Ready Queue to hold jobs that are in ready state. It is implemented by using an ArrayList of LinkedList. The index of the ArrayList is (Priority-1). Each index represents a LinkedList for each priority.
4. `WaitingQueue.java` - This class holds all the jobs that are waiting for I/O.
5. `ExternalEvent.java` - This class stores an external event related information such as command, priority, time estimate, job number etc.
6. `ExternalEvents.java` - This loads all the external events from a file and stores them in a map.
7. `SchedSim.java` - This is the main class that will simulate the priority based round robin scheduling algorithm. It is partially implemented. You will fill in the missing parts that are needed to implement the priority based round-robin algorithm.
