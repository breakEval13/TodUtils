package todcloud.utils.mysql;

import io.swagger.api.util.PropertyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化，模拟加载所有的配置文件
 *
 * @author zhangjianxin
 */
public class DBInitInfo {
    public static List<DBbean> beans = null;

    static {
        beans = new ArrayList<DBbean>();
        // 这里数据 可以从xml 等配置文件进行获取
        // 为了测试，这里我直接写死
        DBbean mysql1 = new DBbean();
        mysql1.setDriverName("com.mysql.jdbc.Driver");
        mysql1.setUrl(PropertyUtil.getProperty("mysql.hostaddr") + "cpms" + "?autoReconnect=true");
        mysql1.setUserName(PropertyUtil.getProperty("mysql.username"));
        mysql1.setPassword(PropertyUtil.getProperty("mysql.password"));

        mysql1.setMinConnections(5);
        mysql1.setMaxConnections(100);

        mysql1.setPoolName("mysqlpool_cpms");
        beans.add(mysql1);

        DBbean mysql2 = new DBbean();
        mysql2.setDriverName("com.mysql.jdbc.Driver");
        mysql2.setUrl(PropertyUtil.getProperty("mysql.hostaddr") + "apimgtdb" + "?autoReconnect=true");
        mysql2.setUserName(PropertyUtil.getProperty("mysql.username"));
        mysql2.setPassword(PropertyUtil.getProperty("mysql.password"));

        mysql2.setMinConnections(5);
        mysql2.setMaxConnections(100);

        mysql2.setPoolName("mysqlpool_apimgtdb");

        beans.add(mysql2);

        DBbean mysql3 = new DBbean();
        mysql3.setDriverName("com.mysql.jdbc.Driver");
        mysql3.setUrl(PropertyUtil.getProperty("mysql.hostaddr") + "regdb" + "?autoReconnect=true");
        mysql3.setUserName(PropertyUtil.getProperty("mysql.username"));
        mysql3.setPassword(PropertyUtil.getProperty("mysql.password"));

        mysql3.setMinConnections(5);
        mysql3.setMaxConnections(100);

        mysql3.setPoolName("mysqlpool_regdb");

        beans.add(mysql3);

    }
}