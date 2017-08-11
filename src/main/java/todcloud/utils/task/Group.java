package todcloud.utils.task;

import java.util.Map;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016-9-21 上午11:08:38

 * @Description ：任务组

 */
public class Group {
    private Map<String, Job> jobs = null;
    public Group() {
        super();
    }
    public Group(Map<String, Job> jobs) {
        super();
        this.jobs = jobs;
    }
    public Map<String, Job> getJobs() {
        return jobs;
    }
    public void setJobs(Map<String, Job> jobs) {
        this.jobs = jobs;
    }
}