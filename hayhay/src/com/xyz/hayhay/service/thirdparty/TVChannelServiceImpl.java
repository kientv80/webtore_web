package com.xyz.hayhay.service.thirdparty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.Channel;
import com.xyz.hayhay.entirty.Program;
import com.xyz.hayhay.entirty.TVStation;

public class TVChannelServiceImpl implements TVChannelService {

	@Override
	public TVStation getTVStation(String stationName) {
		Connection con = JDBCConnection.getInstance().getConnection();
		List<Channel> channels = new ArrayList<>();
		TVStation tv = new TVStation(0,stationName);
		tv.setId(0);// TODO: improve later
		tv.setName(stationName);// only VTV for now
		tv.setLogo("/images/icons/vtv.jpg");
		tv.setChannels(channels);
		String sql = "SELECT * FROM channel  where tvstation = '"+stationName+"'";
		if(stationName == null || stationName.isEmpty())
			sql = "SELECT * FROM channel";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				channels.add(c);
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return tv;
	}

	@Override
	public List<Program> getPrograms(int channelId) {
		Connection con = JDBCConnection.getInstance().getConnection();
		List<Program> programs = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT t.*, c.name as channelName FROM tvprogram t, channel c where  t.channel = c.id and  c.id = " + channelId);
			while (rs.next()) {
				Program p = new Program(rs.getString("time"), rs.getString("name"));
				p.setTitle(rs.getString("title"));
				p.setId(rs.getInt("id"));
				p.setChannelId(rs.getInt("channel"));
				p.setChannelName(rs.getString("channelName"));
				programs.add(p);
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return programs;
	}
	@Override
	public List<Program> getRunningPrograms(String hour, String tv) {
		Connection con = JDBCConnection.getInstance().getConnection();
		List<Program> programs = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			String sql = "SELECT c.name  as channelName, t.* FROM tvprogram t, channel c where c.id = t.channel and t.time like '"+hour+"%'";
			if(tv != null && hour != null){
				sql = "SELECT c.name  as channelName, t.* FROM tvprogram t, channel c where c.id = t.channel and t.time like '"+hour+"%' and c.tvstation='"+tv+"'";
			}else if(tv != null && hour == null){
				sql = "SELECT c.name  as channelName, t.* FROM tvprogram t, channel c where c.id = t.channel and c.tvstation='"+tv+"'";
			}
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Program p = new Program(rs.getString("time"), rs.getString("name"));
				p.setTitle(rs.getString("title"));
				p.setId(rs.getInt("id"));
				p.setChannelId(rs.getInt("channel"));
				p.setChannelName(rs.getString("channelName"));
				programs.add(p);
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return programs;
	}
	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		
	}
}
