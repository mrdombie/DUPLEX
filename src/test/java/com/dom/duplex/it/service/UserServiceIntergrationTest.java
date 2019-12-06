package com.dom.duplex.it.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dom.duplex.repository.domain.api.ApiUser;
import com.dom.duplex.repository.domain.api.ApiUserList;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;

import wiremock.com.google.common.collect.Lists;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8081)
@AutoConfigureMockMvc
public class UserServiceIntergrationTest {

	private static final Gson GSON = new Gson();

	@Autowired
	private MockMvc mvc;

	@Test
	public void testServiceIntergrationSuccess() throws Exception {

		final List<ApiUser> apiUserList = Lists.newArrayList(new ApiUser().setAge(33).setHeight(186).setName("Dom"));
		final ApiUserList userList = new ApiUserList().setApiUserList(apiUserList);

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/dom/thirdparty")).willReturn(
				WireMock.aResponse().withHeader("Content-Type", "application/json").withBody(GSON.toJson(userList))));

		mvc.perform(MockMvcRequestBuilders.get("/thirdparty").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
