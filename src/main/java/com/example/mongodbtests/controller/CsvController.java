package com.example.mongodbtests.controller;

import com.example.mongodbtests.service.CsvService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Log4j2
public class CsvController {

    @Autowired
    CsvService csvService;

    //http://localhost:8082/readcsv
    @GetMapping("/readcsv")
    public ResponseEntity getCsv() throws Exception {
        return ResponseEntity.of(Optional.of(csvService.getFile()));
    }



}
