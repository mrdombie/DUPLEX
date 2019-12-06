package com.dom.duplex.repository.domain.api;

import java.util.ArrayList;
import java.util.List;

public class ApiUserList {

	private List<ApiUser> apiUserList;

	public ApiUserList() {
		apiUserList = new ArrayList<>();
	}

	public List<ApiUser> getApiUserList() {
		return apiUserList;
	}

	public ApiUserList setApiUserList(final List<ApiUser> apiUserList) {
		this.apiUserList = apiUserList;
		return this;
	}
}
