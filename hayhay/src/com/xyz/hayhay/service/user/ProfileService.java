package com.xyz.hayhay.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.xyz.hayhay.db.JDBCConnection;

public class ProfileService {
	private static ProfileService instance;

	public static ProfileService getInstance() {
		if (instance == null)
			instance = new ProfileService();
		return instance;
	}

	public Profile saveProfile(Profile p) throws SQLException {
		String sql = "insert into profile(id,name,avatar,token,first_name,last_name,permissions,declined_permissions)values(?,?,?,?,?,?,?,?)";
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, p.getId() + "");
			stm.setString(2, p.getName() + "");
			stm.setString(3, p.getAvatar() + "");
			stm.setString(4, p.getToken() + "");
			stm.setString(5, p.getFirstName() + "");
			stm.setString(6, p.getLastName() + "");
			stm.setString(7, p.getPermissions() + "");
			stm.setString(8, p.getDeclinedPermissions() + "");
			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return p;// getPerson(Long.valueOf(p.getId()));
	}

	Map<String, Profile> profiles = new HashedMap();

	public Profile getProfile(String id) throws SQLException {
		Profile p = profiles.get(id);
		if (p == null) {
			String sql = "select * from profile where id=?";
			try (Connection con = JDBCConnection.getInstance().getConnection()) {
				try (PreparedStatement stm = con.prepareStatement(sql)) {
					try (ResultSet rs = stm.executeQuery()) {
						while (rs.next()) {
							p = toProfile(rs);
							profiles.put(p.getId(), p);
						}
					}
				}
			}
		}
		return p;
	}

	private Profile toProfile(ResultSet rs) throws SQLException {
		Profile p = new Profile();
		p.setId(rs.getString("id"));
		p.setName(rs.getString("name"));
		p.setAvatar(rs.getString("avatar"));
		p.setFirstName(rs.getString("first_name"));
		p.setLastName(rs.getString("last_name"));
		return p;
	}
}
