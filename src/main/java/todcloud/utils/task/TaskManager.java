package todcloud.utils.task;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2012-9-21 上午11:12:16

 * @Description ：任务管理类

 */
public class TaskManager {
    public final static Map<String, Group> GROUP = new ConcurrentHashMap<String, Group>();

    /**

     * 添加一个任务

     *

     * @param job

     */
    public static void addJob(JobManager job) {
        Date runTime = job.getRunTime();
        String groupName = job.getGroupName();
        String jobName = job.getJobName();
        Date now = new Date();
        Group group = GROUP.get(groupName);
        if (group != null) {
            Job jobPojo = group.getJobs().get(jobName);
            if (jobPojo != null) {
                System.out.println("添加了重复任务");
                return;
            }
        } else {
            group = new Group(new ConcurrentHashMap<String, Job>());
        }
        if (runTime.before(now)) {
            job.run();
            return;
        }

        ScheduledFuture<?> f = null;
        switch (job.getTaskType()) {
            case TaskType.FIXED:
                f = fixed(job.getScheduleds(), job, runTime);
                break;
            case TaskType.LOOP:
                f = loop(job.getScheduleds(), job, runTime, job.getLoopTime());
                break;
        }
        Job jobPojo = group.getJobs().get(jobName);
        if (jobPojo == null) {
            jobPojo = new Job(job, f, group);
        }
        group.getJobs().put(jobName, jobPojo);
        GROUP.put(groupName, group);
    }

    /**

     * 修改任务

     *

     * @param groupName

     *            任务组名称

     * @param jobName

     *            任务名称

     * @param runTime

     *            执行时间

     */
    public static void updateJob(String groupName, String jobName, Date runTime) {
        Group group = GROUP.get(groupName);
        if (group == null) {
            return;
        }

        Job jobPojo = group.getJobs().get(jobName);
        if (jobPojo == null) {
            return;
        }
        JobTask job = (JobTask) jobPojo.getJob();
        ScheduledFuture<?> f = jobPojo.getF();
        f.cancel(true);

        job.setRunTime(runTime);
        switch (job.getTaskType()) {
            case TaskType.FIXED:
                f = fixed(job.getScheduleds(), job, runTime);
                break;
            case TaskType.LOOP:
                f = loop(job.getScheduleds(), job, runTime, job.getLoopTime());
                break;
        }
    }

    /**

     * 删除任务

     *

     * @param groupName

     *            任务组名称

     * @param jobName

     *            任务名称

     */
    public static void deleteJob(String groupName, String jobName) {
        Group group = GROUP.remove(groupName);
        if (group == null) {
            return;
        }

        Job jobPojo = group.getJobs().remove(jobName);
        if (jobPojo == null) {
            return;
        }

        ScheduledFuture<?> f = jobPojo.getF();
        f.cancel(true);
    }

    // 定时执行

    public static final ScheduledFuture<?> fixed(ScheduledExecutorService ses,
                                                 Runnable r, Date d) {
        // 得到时间差

        long delay = d.getTime() - System.currentTimeMillis();
        delay = delay <= 0 ? 1 : delay;
        return ses.schedule(r, delay, TimeUnit.MILLISECONDS);
    }

    // 循环执行

    public static final ScheduledFuture<?> loop(ScheduledExecutorService ses,
                                                Runnable r, Date d, long loopTime) {
        // 得到时间差

        long delay = d.getTime() - System.currentTimeMillis();
        delay = delay <= 0 ? 1 : delay;
        return ses.scheduleAtFixedRate(r, delay, loopTime,
                TimeUnit.MILLISECONDS);
    }
}