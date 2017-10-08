package com.xyz.hayhay.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.xyz.hayhay.db.JDBCConnection;

public class ProfileService {
	private static ProfileService instance;
	public static ProfileService getInstance() {
		if(instance == null)
			instance = new ProfileService();
		return instance;
	}


	public Person addPerson(Person p) throws SQLException {
		String sql = "insert into profile(name,email,avatar,token,domain,first_name,last_name, telco, deviceinfo, deviceid)values(?,?,?,?,?,?,?,?,?,?)";
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, p.getName()+"");
			stm.setString(2, p.getEmail()+"");
			stm.setString(3, p.getImage()+"");
			stm.setString(4, p.getAccessToken()+"");
			stm.setString(5, p.getEmail()+"");
			stm.setString(6, p.getFirstName()+"");
			stm.setString(7, p.getSurname()+"");
			stm.setString(8, p.getTelco()+"");
			stm.setString(9, p.getDeviceinfo()+"");
			stm.setString(10, p.getDeviceid()+"");
			stm.execute();
			rs = stm.getGeneratedKeys();
			if(rs != null){
				while (rs.next()) {
					p.setId(rs.getLong(1));
				}
				rs.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return getPerson(Long.valueOf(p.getId()));
	}

	public Person getPerson(long id) throws SQLException {
		String sql = "select * from profile where id=?";
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		Person p = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setLong(1, id);
			rs = stm.executeQuery();
			while (rs.next()) {
				p = toPerson(rs, con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return p;
	}
	public List<Person> getPersonByDeviceId(String deviceid) throws SQLException {
		String sql = "select * from profile where deviceid=? limit 2";
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Person> person = new ArrayList<>();
		Person p = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, deviceid);
			rs = stm.executeQuery();
			while (rs.next()) {
				person.add(toPerson(rs, con));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return person;
	}
	private Person toPerson(ResultSet rs, Connection con) throws SQLException {
		Person p = new Person();
		p.setId(Long.valueOf(rs.getString("id")));
		p.setName(rs.getString("name"));
		p.setImage(rs.getString("avatar"));
		p.setAccessToken(rs.getString("token"));
		p.setEmail(rs.getString("email"));
		p.setTelco(rs.getString("telco"));
		p.setDeviceid(rs.getString("deviceid"));
		p.setDeviceinfo(rs.getString("deviceinfo"));
		return p;
	}

}
