package com.dom.duplex.service;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.CsvRepository;
import com.dom.duplex.repository.domain.Report;
import com.google.common.io.Resources;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	private CsvRepository csvRepository;

	@Autowired
	private CsvEntryRepository csvEntryRepository;

	public String generateReport() throws JRException {
		final String reportPath = "D:\\";

		final URL resource = Resources.getResource("report.jrxml");

		// Compile the Jasper report from .jrxml to .japser
		final JasperReport jasperReport = JasperCompileManager.compileReport(resource.getPath());

		final int amountOfProcessCvs = csvRepository.findAll().size();
		final int amountOfRowsProcessed = csvEntryRepository.findAll().size();

		final List<Report> count = Arrays.asList(new Report(amountOfRowsProcessed, amountOfProcessCvs));

		final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(count);

		final Map<String, Object> parameters = new HashMap<>();

		final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				jrBeanCollectionDataSource);

		JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Report.pdf");

		return "Report successfully generated @path= " + reportPath;
	}
}
