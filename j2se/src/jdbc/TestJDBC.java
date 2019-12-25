package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TestJDBC {
	static Connection Conn;
	static Statement Stat;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		comparePool();
		//数据库连接池演示
		//pool();
		
		//setConn();
        //createStatement();
        //新增数据
        //insert();
        //删除数据
        //delete();
        //修改数据
        //update();
        //查询数据
        //selectByMap();
        //获取总计count
        //count();
        //分页查询
        //list(2, 5);
        //性能对比
        //comparePerformance();
        //metaData();
        
        //orm演示
        //orm();
        
        //closeDb();
	}
	
	/**
	 * 比较性能
	 */
	public static void comparePool(){
		int number = 150;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		long t1 = System.currentTimeMillis();
		List<Thread> ts1 = new ArrayList<Thread>();

    	for (int i = 0; i < number; i++) {
			Thread t = new Thread(){
				public void run(){
					try {
						String dsn = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
						Connection c = DriverManager.getConnection(dsn, "root", "123456");
						
						String sql = "insert into hero(name, hp, damage) values(?,?,?)";
						PreparedStatement ps = c.prepareStatement(sql);
						ps.setString(1, "cs"+(System.currentTimeMillis() + (Math.random() * 1000)));
						ps.setFloat(2, 100);
						ps.setInt(3, 50);
						
						ps.execute();
						
						ps.close();
						c.close();
					} catch (SQLException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }
					
				}
			};
			t.start();
			ts1.add(t);
		}
    	
    	//等待任务结束
    	try{
    		for (Thread t: ts1) {
    			t.join();
    		}
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}
    	long t2 = System.currentTimeMillis();
		System.out.println("传统线程方式花费的时间：" + (t2 - t1));
		
		t1 = System.currentTimeMillis();
		ConnectionPool cp = new ConnectionPool(70);
		List<Thread> ts2 = new ArrayList<Thread>();
		for(int j = 0; j < number; j++){
			TestThread t = new TestThread("thread " + j, cp);
			t.start();
			ts2.add(t);
		}
		try{
    		for (Thread t: ts2) {
    			t.join();
    		}
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}
		t2 = System.currentTimeMillis();
		System.out.println("连接池方式花费的时间：" + (t2 - t1));
		
	}
	
	/**
	 * 数据库连接池演示
	 */
	public static void pool(){
		ConnectionPool cp = new ConnectionPool(3);
		for (int i = 0; i < 10; i++) {
			new TestThread("thread " + i, cp).start();
		}
	}
	
	/*hero orm*/
	public static Hero get(int id) {
        Hero hero = null;
        
        try {
 
            String sql = "select * from hero where id = ?";
            PreparedStatement ps = Conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
   
            // 因为id是唯一的，ResultSet最多只能有一条记录
            // 所以使用if代替while
            if (rs.next()) {
                hero = new Hero();
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                hero.id = id;
            }
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hero;
   
    }
	
	public static void orm(){
		Hero h = get(11222);
        System.out.println(h.name);
	}
	
	/**
	 * 数据库的元数据
	 */
	public static void metaData(){
		try {
			DatabaseMetaData dbmd = Conn.getMetaData();
			
			// 获取数据库服务器产品名称
            System.out.println("数据库产品名称:\t"+dbmd.getDatabaseProductName());
            // 获取数据库服务器产品版本号
            System.out.println("数据库产品版本:\t"+dbmd.getDatabaseProductVersion());
            // 获取数据库服务器用作类别和表名之间的分隔符 如test.user
            System.out.println("数据库和表分隔符:\t"+dbmd.getCatalogSeparator());
            // 获取驱动版本
            System.out.println("驱动版本:\t"+dbmd.getDriverVersion());
  
            System.out.println("可用的数据库列表：");
            // 获取数据库名称
            ResultSet rs = dbmd.getCatalogs();
            while (rs.next()) {
                System.out.println("数据库名称:\t"+rs.getString(1));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 比较性能
	 */
	public static void comparePerformance(){
		int i = 1;
		int size = 10000;
		int limit = i + size;
		String sql;
		long t1 = System.currentTimeMillis();
		try{
			while(i <= limit){
				sql = "insert into hero(name,hp,damage) values('hero" + i + "', 100, 50)";
				Stat.execute(sql);
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println("statement方式花费的时间：" + (t2 - t1));
	
		limit = i + size;
		t1 = System.currentTimeMillis();
		try{
			sql = "insert into hero(name,hp,damage) values(?,?,?)";
			PreparedStatement ps = Conn.prepareStatement(sql);
			while(i <= limit){
				ps.setString(1, "hero"+i);
				ps.setInt(2, 100);
				ps.setInt(3, 50);
				ps.execute();
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		t2 = System.currentTimeMillis();
		System.out.println("prepareStatement方式花费的时间：" + (t2 - t1));
		
	}
	
	/**
	 * 翻页
	 * @param start
	 * @param count
	 */
	public static void list(int start, int count){
		// 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			String sql = "select * from hero limit ?,?";
			PreparedStatement ps = Conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			//rs = ps.executeQuery();
			//或
			ps.execute();
			rs = ps.getResultSet();
			
			while (rs.next()) {
	            int id = rs.getInt("id");// 可以使用字段名
	            String name = rs.getString(2);// 也可以使用字段的顺序
	            float hp = rs.getFloat("hp");
	            int damage = rs.getInt(4);
	            System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 总计
	 */
	public static void count(){
        // 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			String sql = "select count(*) from user";
			rs = Stat.executeQuery(sql);
			int total = 0;
			if (rs.next()) {
				total = rs.getInt(1);
	            System.out.println("数据条数总数：" + total);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	/**
	 * 查询
	 */
	public static void selectByMap(){
        // 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			String name = "dashen";
			String password = "thisispassword";
			String sql = "select * from user where name='" + name + "' and password='"+password+"'";
			rs = Stat.executeQuery(sql);
			if (rs.next()) {
	            System.out.println("密码正确");
	        }else{
	        	System.out.println("密码错误");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	/**
	 * 查询
	 */
	public static void select(){
        // 执行查询语句，并把结果集返回给ResultSet
		try {
			ResultSet rs;
			String sql = "select * from hero";
			rs = Stat.executeQuery(sql);
			while (rs.next()) {
	            int id = rs.getInt("id");// 可以使用字段名
	            String name = rs.getString(2);// 也可以使用字段的顺序
	            float hp = rs.getFloat("hp");
	            int damage = rs.getInt(4);
	            System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	/**
	 * 统一封装执行方法
	 * @param sql
	 */
	public static void execute(String sql){
		try{
			Stat.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新数据
	 */
	public static void update(){
		execute("update hero set hp=150 where id=6");
		System.out.println("3、执行修改数据");
	}
	
	/**
	 * 新增数据
	 */
	public static void delete(){
		execute("delete from hero where id = 3");
		System.out.println("3、执行删除数据");
	}
	
	/**
	 * 新增数据
	 */
	public static void insert(){
		String sql = "insert into hero(name, hp, damage) values(?,?,?)";
		try {
			PreparedStatement ps = Conn.prepareStatement(sql);
			ps.setString(1, ""+(System.currentTimeMillis() + (Math.random() * 1000)));
			ps.setFloat(2, 100);
			ps.setInt(3, 50);
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				//System.out.println("新增数据的id是：" + rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		//System.out.println("3、执行插入10条语句成功");
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
			System.out.println("4、关闭连接");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建statement语句
	 */
	public static void createStatement(){
		try {
			Stat = Conn.createStatement();
			System.out.println("2、创建获取 Statement对象： " + Stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	//建立数据库连接
	public static void setConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String dsn = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
			Conn = DriverManager.getConnection(dsn, "root", "123456");
			
			System.out.println("1、连接成功，获取连接对象：" + Conn);
		} catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
