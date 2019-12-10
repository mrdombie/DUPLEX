package com.dom.duplex.client;

import org.springframework.http.ResponseEntity;

public interface ThirdParty {

	/**
	 * Get users for third party external API
	 *
	 * @return {@link ResponsEntity} container the list of CVS to parse
	 */

	ResponseEntity<String> getUserCsvs();
}
