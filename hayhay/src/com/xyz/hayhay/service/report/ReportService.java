package com.xyz.hayhay.service.report;

import java.util.List;

import com.xyz.hayhay.entirty.Report;

public interface ReportService {
	public List<Report> getCategoryReport(String time) throws Exception;
}
