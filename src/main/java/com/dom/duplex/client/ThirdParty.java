package com.dom.duplex.client;

import org.springframework.http.ResponseEntity;

public interface ThirdParty {
	ResponseEntity<String> getUserCsvs();
}
