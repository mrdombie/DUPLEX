package com.dom.duplex.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
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
	public ResponseEntity<String> getUserCsvs() {

		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

		final HttpEntity<String> entity = new HttpEntity<>(headers);

		return restTemplate.exchange(url + PATH, HttpMethod.GET, entity, String.class);
	}
}
