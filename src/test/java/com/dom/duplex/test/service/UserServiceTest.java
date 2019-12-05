package com.dom.duplex.test.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.unitils.reflectionassert.ReflectionAssert;

import com.dom.duplex.client.ThirdyPartyApiClient;
import com.dom.duplex.repository.UserRepository;
import com.dom.duplex.repository.domain.RequestStatus;
import com.dom.duplex.repository.domain.User;
import com.dom.duplex.repository.domain.api.ApiUser;
import com.dom.duplex.repository.domain.api.ApiUserList;
import com.dom.duplex.service.UserService;

@SpringBootTest
public class UserServiceTest {

    private static final ApiUserList API_USERS_LIST = new ApiUserList();
    private static final List<ApiUser> API_USERS = new ArrayList<>();
    private static final int AGE = 33;
    private static final int HEIGHT = 186;
    private static final String NAME = "dom";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ThirdyPartyApiClient thirdyPartyApiClient;

    @Captor
    private ArgumentCaptor<List<User>> capturedUsers;

    @BeforeEach
    public void setup() {
	API_USERS.add(new ApiUser().setAge(AGE).setHeight(HEIGHT).setName(NAME));

	ReflectionTestUtils.setField(API_USERS_LIST, "apiUserList", API_USERS);
	Mockito.when(thirdyPartyApiClient.getUsers()).thenReturn(API_USERS_LIST);
    }

    @Test
    public void testGetApiUsers() {
	userService.consumeThirdPartyUsers();

	Mockito.verify(thirdyPartyApiClient).getUsers();
	Mockito.verify(userRepository).saveAll(capturedUsers.capture());

	ReflectionAssert.assertLenientEquals(capturedUsers.getValue(), getApiUsers());
    }

    private List<User> getApiUsers() {
	return Lists.newArrayList(
		new User().setAge(AGE).setHeight(HEIGHT).setName(NAME).setRequestStatus(RequestStatus.HOLDNG));
    }

}
