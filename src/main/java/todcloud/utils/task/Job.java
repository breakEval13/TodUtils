package todcloud.utils.task;

import java.util.concurrent.ScheduledFuture;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2012-9-21 上午11:08:27

 * @Description ：Please describe this document

 */
public class Job {
    private JobManager job = null;
    private ScheduledFuture<?> f = null;
    private Group group = null;

    public Job() {
        super();
    }

    public Job(JobManager job, ScheduledFuture<?> f, Group group) {
        super();
        this.job = job;
        this.f = f;
        this.group = group;
    }

    public JobManager getJob() {
        return job;
    }

    public void setJob(JobManager job) {
        this.job = job;
    }

    public ScheduledFuture<?> getF() {
        return f;
    }

    public void setF(ScheduledFuture<?> f) {
        this.f = f;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}