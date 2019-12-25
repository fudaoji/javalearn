package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	int size;
	List<Connection> cs = new ArrayList<Connection>();
	
	public ConnectionPool(int size){
		this.size = size;
		this.init();
	}
	
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			String dsn = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
			for (int i = 0; i < this.size; i++) {
				Connection c = DriverManager.getConnection(dsn, "root", "123456");
				cs.add(c);
			}
			//System.out.println("连接池初始化成功");
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public synchronized Connection getConnection(){
		while(cs.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Connection c = cs.remove(0);
		//System.out.println(c + "被调用");
		return c;
	}
	
	public synchronized void returnConnection(Connection c){
		cs.add(c);
		this.notifyAll(); //提醒所有等待的线程，此连接已释放
	}
}
