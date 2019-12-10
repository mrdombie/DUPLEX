package com.dom.duplex.client;

import com.dom.duplex.repository.domain.api.DataApiUser;

public interface PresentationApi {

	/**
	 * Send a user to the presentation tier.
	 * 
	 * @param users
	 */

	void sendDataUser(DataApiUser users);
}
