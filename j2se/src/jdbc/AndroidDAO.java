package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidDAO {
	static String table = "android";

	public AndroidDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8", "root",
				"123456");
	}

	public int getTotal() {
		int total = 0;
		try (Connection c = getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from " + table;

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			System.out.println("total:" + total);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public void add(Android ad) {

		String sql = "insert into " + table + " values(null,?,?,?)";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, ad.receive);
			ps.setString(2, ad.response);

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				ad.id = id;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void update(Android ad) {

		String sql = "update " + table + " set name= ?, hp = ? , damage = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, ad.receive);
			ps.setString(2, ad.response);
			ps.setInt(3, ad.id);

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {

		try (Connection c = getConnection(); Statement s = c.createStatement();) {

			String sql = "delete from " + table + " where id = " + id;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Android get(int id) {
		Android ad = null;

		try (Connection c = getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from " + table + " where id = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				ad = new Android();
				ad.receive = rs.getString(2);
				ad.response = rs.getString(3);
				ad.id = id;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ad;
	}

	public List<Android> selectByMap(HashMap<String, String> hm) {
		List<Android> ads = new ArrayList<Android>();

		String whereSql = "where 1=1";

		for (Map.Entry<String, String> entry : hm.entrySet()) {
			whereSql += (" and " + entry.getKey() + "='" + entry.getValue() + "'");
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());
		}

		String sql = "select * from " + table + " " + whereSql + " order by id desc";
		// System.out.println(sql);

		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();
			Android ad;
			while (rs.next()) {
				ad = new Android();
				ad.id = rs.getInt(1);
				ad.receive = rs.getString(2);
				ad.response = rs.getString(3);
				ads.add(ad);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ads;
	}

	public List<Android> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Android> list(int start, int count) {
		List<Android> ads = new ArrayList<Android>();

		String sql = "select * from " + table + " order by id desc limit ?,? ";

		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Android ad = new Android();
				ad.id = rs.getInt(1);
				ad.receive = rs.getString(2);
				ad.response = rs.getString(3);
				ads.add(ad);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ads;
	}
}
