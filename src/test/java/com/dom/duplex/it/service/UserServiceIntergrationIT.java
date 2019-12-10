package com.dom.duplex.it.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
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

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UserServiceIntergrationIT {

	@Autowired
	private MockMvc mvc;

	@Test
	@DatabaseSetup(value = "/csv.xml", type = DatabaseOperation.INSERT)
	@ExpectedDatabase("/csv_expected.xml")
	public void testServiceIntergrationSuccess() throws Exception {

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/dom/thirdparty")).willReturn(
				WireMock.aResponse().withHeader("Content-Type", "multipart/form-data").withBodyFile("user.csv")));

		mvc.perform(MockMvcRequestBuilders.get("/thirdparty").accept(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk());
	}
}
