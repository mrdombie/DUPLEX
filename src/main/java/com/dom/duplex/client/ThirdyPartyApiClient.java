package com.dom.duplex.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdyPartyApiClient implements ThirdParty {

	private static final String PATH = "/thirdparty";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${thirdparty.url}")
	private String url;

	@Override
	public ResponseEntity<byte[]> getUsers() {
		return restTemplate.exchange(url + PATH, HttpMethod.GET, null, byte[].class);
	}
}
