package com.dom.duplex.repository.domain.api;

import java.util.ArrayList;
import java.util.List;

public class ApiUserList {

    private final List<ApiUser> apiUserList;

    public ApiUserList() {
	apiUserList = new ArrayList<>();
    }

    public List<ApiUser> getApiUserList() {
	return apiUserList;
    }
}
