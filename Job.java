public class Job {
    private int jobNumber;
    private int priority;
    private int timeRemaining;
    private int initialTime;
    private int totalTime;
    private int timesEnteredCPU;

    public Job(int jobNumber, int priority, int timeRemaining) {
        this.jobNumber = jobNumber;
        this.priority = priority;
        this.timeRemaining = timeRemaining;
        this.initialTime = timeRemaining;
        this.totalTime = 0;
        this.timesEnteredCPU = 0;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getInitialTime() {
        return initialTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTimesEnteredCPU() {
        return timesEnteredCPU;
    }

    public void setTimesEnteredCPU(int timesEnteredCPU) {
        this.timesEnteredCPU = timesEnteredCPU;
    }
}
