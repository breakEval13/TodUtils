package todcloud.utils.task;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2013年11月26日 下午10:56:59

 * @Description ：Please describe this document

 */
public class MyTask extends JobTask {

    public MyTask(Date runDate, String groupName, String jobName,
                  long loopTime, int taskType, ScheduledExecutorService scheduleds,
                  Object obj) {
        super(runDate, groupName, jobName, loopTime, taskType, scheduleds, obj);
    }

    @Override
    public void execution(JobTask jobTask) {
        System.out.println("任务执行");
    }
}