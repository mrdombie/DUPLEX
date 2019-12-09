package com.dom.duplex.it.controller.csv;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class CsvControllerFailureIT {

	@Autowired
	private MockMvc mvc;

	@DatabaseSetup(value = "/csv.xml", type = DatabaseOperation.INSERT)
	@ExpectedDatabase("/csv_failure_expected.xml")
	@Test
	public void testCsvInvalidCsvUploadFailureTest() throws Exception {

		final File file = new File("src/test/resources/invalid_user.csv");
		final InputStream inputStream = new FileInputStream(file);

		final MockMultipartFile csvFIle = new MockMultipartFile("csvData", "test.csv", "text/plain", inputStream);

		mvc.perform(
				MockMvcRequestBuilders.multipart("/upload").file("file", csvFIle.getBytes()).characterEncoding("UTF-8"))
				.andExpect(status().isBadRequest());
	}
}
