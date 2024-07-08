package com.example.mongodbtests.controller;


import com.example.mongodbtests.aop.ErrorHandler;
import com.example.mongodbtests.aop.LogExecutionTime;
import com.example.mongodbtests.exception.MyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
@Log4j2
public class AopTestController {


    @GetMapping
    @LogExecutionTime
    public ResponseEntity<?> execTime(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e);
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/error")
    @ErrorHandler
    public ResponseEntity<?> error(
            @RequestParam("error")String error
    ) throws Exception {
        if ("error".equalsIgnoreCase(error)){
            throw new MyException("custom error");
        }
        return ResponseEntity.ok("OK");
    }


}
