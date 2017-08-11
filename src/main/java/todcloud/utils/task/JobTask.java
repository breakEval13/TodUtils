package todcloud.utils.task;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2012-9-21 下午12:18:48

 * @Description ：任务基类

 */
public abstract class JobTask extends JobManager {

    /**

     * 循环执行

     *

     * @param runDate

     * @param groupName

     * @param jobName

     * @param loopTime

     * @param scheduleds

     * @param obj

     * @return

     */
    public JobTask(Date runDate, String groupName, String jobName,
                   long loopTime, int taskType, ScheduledExecutorService scheduleds,
                   Object obj) {
        setRunTime(runDate);
        setGroupName(groupName);
        setJobName(jobName);
        setLoopTime(loopTime);
        setScheduleds(scheduleds);
        setTaskType(taskType);
    }

    /**

     * 定点执行

     *

     * @param runDate

     * @param groupName

     * @param jobName

     * @param scheduleds

     * @param obj

     * @return

     */
     public JobTask(Date runDate, String groupName,

     String jobName, ScheduledExecutorService scheduleds, Object obj) {

     setRunTime(runDate);

     setGroupName(groupName);

     setJobName(jobName);

     setScheduleds(scheduleds);

     setTaskType(TaskType.FIXED);

     }


    public abstract void execution(JobTask jobTask);

}
