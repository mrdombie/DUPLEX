package com.dom.duplex.client;

import com.dom.duplex.repository.domain.api.DataApiUser;

public interface PresentationApi {
	void sendDataUser(DataApiUser users);
}
