package com.dom.duplex.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dom.duplex.service.UserService;

@RestController
public class ThirdPartyApiController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/thirdparty")
	public void getThirdPartyUsers() {
		userService.consumeThirdPartyUsers();
	}

}
