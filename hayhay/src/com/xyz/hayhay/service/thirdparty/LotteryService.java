package com.xyz.hayhay.service.thirdparty;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.db.JDBCConnection;

public class LotteryService {
	static SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
	@SuppressWarnings("unchecked")
	public static JSONArray getLottery(Date date,String agency) throws Exception{
		JSONArray result = new JSONArray();
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from lottery where date = ? and code=?  order by id desc";
		if(agency.isEmpty() && date != null){
			sql = "Select * from lottery where date = ?  order by id desc";
			stm = con.prepareStatement(sql);
			stm.setDate(1, date);
		}else if(!agency.isEmpty() && date == null){
			sql = "Select * from lottery where code = ?  order by id desc limit 30";
			stm = con.prepareStatement(sql);
			stm.setString(1, agency);
		}else if(!agency.isEmpty() && date != null){
			stm = con.prepareStatement(sql);
			stm.setDate(1, date);
			stm.setString(2, agency);
		}
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			JSONObject obj = new JSONObject();
			obj.put("code", rs.getString("code"));
			obj.put("name", rs.getString("name"));
			obj.put("data", (JSONArray)new JSONParser().parse(rs.getString("data")));
			obj.put("date", df.format(rs.getDate("date")));
			result.add(obj);
		}
		rs.close();
		stm.close();
		con.close();
		return result;
	}
}
