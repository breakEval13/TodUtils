package todcloud.utils.task;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2012-9-21 上午11:11:54

 * @Description ：Please describe this document

 */
public class JobManager implements Runnable {
    /** 执行时间 */
    private Date runTime = null;
    /** 任务关联对象 */
    private Object obj = null;
    /** 组名称 */
    private String groupName = "";
    /** 任务名称 */
    private String jobName = "";
    /** 执行线程池 */
    private ScheduledExecutorService scheduleds = null;
    /** 任务类型 */
    private int taskType;
    /** 时间间隔 */
    private long loopTime;

    public JobManager() {
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ScheduledExecutorService getScheduleds() {
        return scheduleds;
    }

    public void setScheduleds(ScheduledExecutorService scheduleds) {
        this.scheduleds = scheduleds;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public long getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(long loopTime) {
        this.loopTime = loopTime;
    }

    public void run() {
        JobTask jobTask = (JobTask) this;
        // 执行任务

        jobTask.execution(jobTask);
        // 删除任务对象

        Group group = TaskManager.GROUP.get(groupName);
        if (group == null) {
            return;
        }
        switch (jobTask.getTaskType()) {
            case TaskType.FIXED:
                group.getJobs().remove(jobName);
                if (group.getJobs().isEmpty()) {
                    TaskManager.GROUP.remove(groupName);
                }
                break;
            case TaskType.LOOP:
                break;
        }
    }
}
