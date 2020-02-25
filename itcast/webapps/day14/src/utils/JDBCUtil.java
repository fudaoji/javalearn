package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * druid连接池的使用封装
 */
public class JDBCUtil {
    private static DataSource ds;

    static {
        try {
            //读取配置文件
            Properties pro = new Properties();
            ClassLoader cll = JDBCUtil.class.getClassLoader();
            InputStream is = cll.getResourceAsStream("druid.properties");

            pro.load(is);

            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get connection handler
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * release source
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt, conn);
    }

    /**
     * overload two parameters
     *
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
