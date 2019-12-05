package com.dom.duplex.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dom.duplex.repository.domain.api.ApiUserList;

@Service
public class ThirdyPartyApiClient implements ThirdParty {

	private static final String PATH = "/thirdparty";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${thirdparty.url}")
	private String url;

	@Override
	public ApiUserList getUsers() {
		return restTemplate.getForObject(url + PATH, ApiUserList.class);
	}
}
