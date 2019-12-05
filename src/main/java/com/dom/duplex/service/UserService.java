package com.dom.duplex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dom.duplex.client.ThirdyPartyApiClient;
import com.dom.duplex.repository.UserRepository;
import com.dom.duplex.repository.domain.RequestStatus;
import com.dom.duplex.repository.domain.User;
import com.dom.duplex.repository.domain.api.ApiUserList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThirdyPartyApiClient thirdyPartyApiClient;

    @Scheduled(fixedRate = 4000)
    public void consumeThirdPartyUsers() {

	final ApiUserList apiUsers = thirdyPartyApiClient.getUsers();

	final List<User> users = apiUsers.getApiUserList().stream().map(u -> new User().setAge(u.getAge())
		.setHeight(u.getHeight()).setName(u.getName()).setRequestStatus(RequestStatus.HOLDNG))
		.collect(Collectors.toList());

	userRepository.saveAll(users);
    }
}
