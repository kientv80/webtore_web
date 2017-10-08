package com.xyz.hayhay.worker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.TelcoCode;

public class FindLuckyUser {

	public static void findLuckyuser() {
		/*List<String> visitorIds = TrackingServiceImpl.getVisitorIdsForToday();
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int max;
		if (visitorIds.size() > 0)
			max = visitorIds.size() - 1;
		else
			return;

		int min = 0;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		String luckyId = visitorIds.get(randomNum);

		if (luckyId != null && !luckyId.isEmpty()) {
			Connection con = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = null;
			Person p;
			try {
				Statement stm2 = con.createStatement();
				ResultSet rs = stm2.executeQuery("select * from luckyuser where lucky_date='" + DateTimeUtil.ddMMyyyy.format(new Date(System.currentTimeMillis())) + "'");
				boolean alreadyRun = false;
				while (rs.next()) {
					alreadyRun = true;
				}
				stm2.close();
				rs.close();
				if (!alreadyRun) {
					p = ProfileService.getInstance().getPerson(Long.valueOf(luckyId));
					if (p != null) {
						TelcoCode code = getTelcoCode(p.getTelco(), "uid");
						if (code == null) {
							code = new TelcoCode(0, p.getTelco(), "Lien he hotro@360hay.com", 0);
						}
						stm = con.prepareStatement("insert into luckyuser(profile_id,name,email,image,lucky_date, telco,value,mobile_code)values(?,?,?,?,?,?,?,?)");
						stm.setString(1, p.getId() + "");
						stm.setString(2, p.getName() + "");
						stm.setString(3, p.getEmail() + "");
						stm.setString(4, p.getImage() + "");
						stm.setString(5, DateTimeUtil.ddMMyyyy.format(new Date(System.currentTimeMillis())));
						stm.setString(6, code.getTelco());
						stm.setInt(7, code.getValue());
						stm.setString(8, code.getCode());
						stm.execute();
						System.out.println("Lucky user for today:" + p.getName() + " - " + p.getEmail() + " - " + p.getId());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					if (stm != null)
						stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Finished");
		}*/
	}
	
	public static TelcoCode getTelcoCode(String telco, String fromuid) throws Exception {
		if (!isLucky()) {
			return null;
		}
		Connection con = JDBCConnection.getInstance().getConnection();
		Statement stm = null;
		String sql = "select * from telcocode where telco='" + telco.toLowerCase() + "' and status='available' limit 1";
		stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		TelcoCode code = null;
		while (rs.next()) {
			code = new TelcoCode(rs.getInt("id"), rs.getString("telco"), rs.getString("code"), rs.getInt("value"));
		}
		if (code != null)
			stm.execute("update telcocode set status='used' where id=" + code.getId());
		rs.close();
		stm.close();
		con.close();
		return code;
	}
	private static int count = 0;
	private static boolean isLucky() {
		if(count == 20){
			System.out.println("Congrast count = 20");
			count = 0;
			return true;
		}
		
		String time = System.currentTimeMillis() + "";
		System.out.println(time);
		time = time.substring(time.length() - 2, time.length());
		System.out.println(time);
		Random rand = new Random();
		int min = 0;
		int max = 99;
		String randomNum = (rand.nextInt((max - min) + 1) + min) + "";
		System.out.println(randomNum);
		if (randomNum.equals(time)) {
			System.out.println("Congrast");
			count = 0;
			return true;
		}
		count ++;
		return false;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		int min = 0;
		int max = 999999;
		List<Integer> codes = new ArrayList<Integer>();
		int code = 0;
		while (codes.size() < 1000) {
			code = rand.nextInt((max - min) + 1) + min;
			if (!codes.contains(code)) {
				codes.add(code);
			}
		}
		StringBuilder b = new StringBuilder();
		StringBuilder b2 = new StringBuilder();
		for (int i : codes) {
			b.append(i).append("\n");
			b2.append(i).append(",");
		}
		System.out.println(b2.toString());
		System.out.println(b.toString());

	}
}
