
public class CPU {
    
    private Job job;

    public CPU() {
        
    }

    public Job getRunningJob() {
        return job;
    }

    public Job getJob() {
        return job;
    }

    public void setRunningJob(Job job) {
        this.job = job;
    }

    public boolean isEmpty() {
        return job == null;
    }

    public void removeJob() {
        this.job = null;
    }
    
    public void execute() {
        if(job!=null)
            job.execute();
    }
}
