package com.dom.duplex.endpoints;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dom.duplex.repository.UserRepository;
import com.dom.duplex.repository.domain.User;
import com.dom.duplex.utils.CSVReader;

@RestController
public class CsvController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/upload", consumes = "test/csv")
    public void uploadCsv(@RequestBody final InputStream body) throws IOException {
	userRepository.saveAll(CSVReader.read(User.class, body));
    }

}
