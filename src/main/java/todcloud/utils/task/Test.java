package todcloud.utils.task;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import todcloud.utils.io.SK_Date;

import java.util.Date;
import java.util.concurrent.Executors;


/**

 * @UserName : SandKing

 * @DataTime : 2013年11月26日 下午10:55:53

 * @Description ：任务调度器测试

 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        MyTask mytask = new MyTask(SK_Date.addMillisSecond(5000, new Date()),
                "Group", "task1", 1000, TaskType.LOOP,
                Executors.newScheduledThreadPool(8), null);
        // 添加一个任务

        TaskManager.addJob(mytask);
        // // 删除一个任务

        Thread.sleep(10000);
        TaskManager.deleteJob("Group", "task1");
        // // 修改一个任务

        // TaskManager.updateJob("Group", "task1", SK_Date.addDay(1, new

        // Date()));

    }
}