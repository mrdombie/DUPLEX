package com.dom.duplex.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dom.duplex.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/report")
	public String report() throws JRException {
		return reportService.generateReport();
	}
}
