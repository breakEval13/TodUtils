package todcloud.utils.mysql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Night on 2017/6/28.
 */
public class PoolConn implements AutoCloseable{

    private ConnectionPoolManager manager;
    private Connection conn;
    private String pollName;

    public PoolConn(String pollName) {
        this.pollName = pollName;
        this.manager = ConnectionPoolManager.getInstance();
        this.conn = manager.getConnection(pollName);
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    public void close() {
        try {
            // 释放 连接
            this.manager.getPool(this.pollName).releaseConn(conn);
        } catch (SQLException e) {
            System.out.println("Warning "+"向连接池释放连接失败");
            e.printStackTrace();
        }
    }
}
