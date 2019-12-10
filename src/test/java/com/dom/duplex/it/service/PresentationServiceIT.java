package com.dom.duplex.it.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
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
public class PresentationServiceIT {

	@Autowired
	private MockMvc mvc;

	@Test
	@DatabaseSetup(value = "/presentation/csv.xml", type = DatabaseOperation.INSERT)
	@ExpectedDatabase("/presentation/csv_expected.xml")
	public void testPresentationIntergrationSuccess() throws Exception {

		WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/v1/data/presentation"))
				.willReturn(WireMock.aResponse().withStatus(200)));

		mvc.perform(MockMvcRequestBuilders.post("/presentation")).andExpect(status().isOk());
	}
}
