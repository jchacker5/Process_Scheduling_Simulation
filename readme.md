# Process Scheduling Simulation Program
## Developer Information
**Name**: Joe Defendre  
**Course Section**: Comp 350-003

## Overview
This Java program simulates a preemptive priority-based round-robin scheduling algorithm for a simple operating system. The program considers two resources, a CPU and a disk, and operates on an undetermined number of jobs. Jobs have attributes like job number, priority, and maximum time remaining. The simulation handles five different commands to manipulate the state of jobs in the system: Create (J), Start I/O (W), Complete I/O (R), Complete current job (C), and Terminate job (T).

## How to Use
1. **Compile the Code**: Compile both `Job.java` and `Scheduler.java`.
2. **Run the Program**: Run `Scheduler.java`.
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

