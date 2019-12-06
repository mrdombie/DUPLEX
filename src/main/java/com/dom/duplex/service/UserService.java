package com.dom.duplex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dom.duplex.client.ThirdyPartyApiClient;
import com.dom.duplex.repository.CsvCrud;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.RequestStatus;
import com.dom.duplex.repository.domain.api.ApiUserList;

@Service
public class UserService {

	@Autowired
	private CsvCrud crudRepository;

	@Autowired
	private ThirdyPartyApiClient thirdyPartyApiClient;

	@Scheduled(fixedRate = 4000)
	public void consumeThirdPartyUsers() {

		final ApiUserList apiUsers = thirdyPartyApiClient.getUsers();

		final List<CsvEntry> users = apiUsers.getApiUserList().stream().map(u -> new CsvEntry().setAge(u.getAge())
				.setHeight(u.getHeight()).setName(u.getName()).setRequestStatus(RequestStatus.HOLDNG))
				.collect(Collectors.toList());

		crudRepository.save(users);
	}
}
