package com.dom.duplex.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dom.duplex.repository.domain.api.DataApiUser;

@Service
public class PresentationApiClient implements PresentationApi {

	private static final String PATH = "/presentation";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${data.url}")
	private String url;

	@Override
	public void sendDataUser(final DataApiUser user) {
		restTemplate.postForEntity(url + PATH, user, String.class);
	}
}
