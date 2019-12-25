package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	static Connection Conn;
	static Statement Stat;

	public Db() {
		setConn();
		createStatement();
	}

	public Db(String username, String password, String db) {
		setConn(username, password, db);
		createStatement();
	}

	/**
	 * 关闭statement和连接con
	 */
	public static void closeDb(){
		try{
			if(Stat != null){
				Stat.close();
			}
			if(Conn != null){
				Conn.close();
			}
			//System.out.println("4、关闭连接");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建statement语句
	 */
	public static void createStatement() {
		try {
			Stat = Conn.createStatement();
			// System.out.println("2、创建获取 Statement对象： " + Stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 建立数据库连接
	public static void setConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String dsn = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
			Conn = DriverManager.getConnection(dsn, "root", "123456");

			// System.out.println("1、连接成功，获取连接对象：" + Conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 建立数据库连接
	public static void setConn(String username, String password, String db) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String dsn = "jdbc:mysql://localhost:3306/" + db + "?characterEncoding=UTF-8";
			Conn = DriverManager.getConnection(dsn, username, password);

			// System.out.println("1、连接成功，获取连接对象：" + Conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 总计
	 */
	public int count(String sql){
        // 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			rs = Stat.executeQuery(sql);
	
			if (rs.next()) {
	            return rs.getInt(1);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
        
	}
	
	/**
	 * 查询
	 * @return 
	 */
	public ResultSet select(String sql){
        // 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			rs = Stat.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   
	}
	
	
	/**
	 * 更新数据
	 * @return 
	 */
	public int update(String sql){
		if(execute(sql)){
			try {
				int count = Stat.getUpdateCount();
				return count;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 删除数据
	 * @return 
	 */
	public boolean delete(String sql){
		return execute(sql);
	}
	
	/**
	 * 新增数据
	 */
	public int insert(String sql){
		if(execute(sql)){
			try {
				ResultSet rs = Stat.getGeneratedKeys();
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 统一封装执行方法
	 * @param sql
	 * @return 
	 */
	private static boolean execute(String sql){
		try{
			return Stat.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
