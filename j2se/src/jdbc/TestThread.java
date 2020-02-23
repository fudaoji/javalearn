package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestThread extends Thread {
	private ConnectionPool cp;
	
	public TestThread(String name, ConnectionPool cp) {
        super(name);
        this.cp = cp;
    }
	
	public void run(){
		Connection c = cp.getConnection();
        System.out.println(this.getName()+ ":\t 获取了一根连接，并开始工作"  );
        
		String sql = "insert into hero(name, hp, damage) values(?,?,?)";
		try (PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, "pool"+(System.currentTimeMillis() + (Math.random() * 1000)));
			ps.setFloat(2, 100);
			ps.setInt(3, 50);
			
			Thread.sleep(1000);
			//ps.execute();
        } catch (SQLException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        cp.returnConnection(c); //执行完释放连接
	} 
}
