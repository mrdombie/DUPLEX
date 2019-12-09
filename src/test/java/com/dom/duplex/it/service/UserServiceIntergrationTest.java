package com.dom.duplex.it.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
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
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UserServiceIntergrationTest {

	private static final Gson GSON = new Gson();

	@Autowired
	private MockMvc mvc;

	@Test
	@DatabaseSetup(value = "/csv.xml", type = DatabaseOperation.INSERT)
	@ExpectedDatabase("/csv_expected.xml")
	public void testServiceIntergrationSuccess() throws Exception {

		final File file = new File("src/test/resources/user.csv");
		final InputStream inputStream = new FileInputStream(file);

		final MockMultipartFile csvFIle = new MockMultipartFile("csvData", "test.csv", "multipart/form-data",
				inputStream);

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/dom/thirdparty")).willReturn(
				WireMock.aResponse().withHeader("Content-Type", "application/json").withBody(csvFIle.getBytes())));

		mvc.perform(MockMvcRequestBuilders.get("/thirdparty").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
