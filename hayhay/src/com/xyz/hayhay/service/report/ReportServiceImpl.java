package com.xyz.hayhay.service.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.Report;
import com.xyz.hayhay.util.DateTimeUtil;

public class ReportServiceImpl implements ReportService {

	private static ReportService instance;

	public static ReportService getInstance() {
		if (instance == null)
			instance = new ReportServiceImpl();
		return instance;
	}

	@Override
	public List<Report> getCategoryReport(String time) throws Exception {
		List<String> keys = null;
		String prefix = "";
		if ("thisweek".equals(time)) {
			keys = DateTimeUtil.getDaysOfThisWeek();
			prefix = "D_";
		} else if ("lastweek".equals(time)) {
			keys = DateTimeUtil.getDayOfLastWeek();
			prefix = "D_";
		} else if ("today".equals(time)) {
			keys = DateTimeUtil.getHoursOfToday();
			prefix = "H_";
		} else if ("yesterday".equals(time)) {
			keys = DateTimeUtil.getHoursOfYesterDay();
			prefix = "H_";
		} else if ("thismonth".equals(time)) {
			keys = DateTimeUtil.getDayOfThisMonth();
			prefix = "D_";
		} else if ("lastmonth".equals(time)) {
			keys = DateTimeUtil.getDayOfLastMonth();
			prefix = "D_";
		} else {
			keys = DateTimeUtil.getMonthsOfThisYear();
			prefix = "M_";
		}

		Connection con = JDBCConnection.getInstance().getConnection();
		Statement stm = con.createStatement();
		List<Report> reports = new ArrayList<>();
		List<String> cates = Arrays.asList("HUONGTHIEN,SOFTSKILLS,HAY,CUOI,TINTRONGNUOC,TINQUOCTE,ECONOMY,CONGNGHE,SPORTNEWS,NGOISAO,SUCKHOE,TINHYEU,WN_HOME,WN_BIZ,WN_TECH,TVPROGRAME,VTV,VTVCAB,HNTV,HTV,VTC,NGOISAO.NET,TUOITRE.VN,VNEXPRESS.NET,M.ICTNEWS.VN,SUCKHOEGIADINH.COM.VN,THETHAO247.VN,DANTRI.COM.VN,THETHAOTV.VN,VNECONOMY.VN".split(","));
		int sumAll = 0;
		for (String cate : cates) {
			StringBuilder where = new StringBuilder();
			for (String key : keys) {
				where.append("'" + prefix + key + "-" + cate + "',");
			}
			
			String sql = "select * from tracking where id in(" + where.substring(0, where.length() - 1) + ")";
			System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			int sum = 0;
			while (rs.next()) {
				Report rp = new Report();
				rp.setItemDes(rs.getString("id"));
				rp.setCount(rs.getInt("count"));
				reports.add(rp);
				sum = sum + rp.getCount();
			}
			sumAll = sumAll + sum;
			Report rp = new Report();
			rp.setItemName("Sum " + cate);
			rp.setItemDes("Sum " + cate);
			rp.setCount(sum);
			reports.add(rp);
			rs.close();
		}
		Report rp = new Report();
		rp.setItemName("Sum ALL");
		rp.setItemDes("Sum ALL");
		rp.setCount(sumAll);
		reports.add(rp);
		
		stm.close();
		con.close();
		return reports;
	}

}
