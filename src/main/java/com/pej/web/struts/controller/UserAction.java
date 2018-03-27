package com.pej.web.struts.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.pej.web.struts.Application;
import com.pej.web.struts.model.Quote;
import com.pej.web.struts.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import com.pej.web.struts.model.User;
import com.pej.web.struts.service.ServiceFacade;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;


@Component
public class UserAction extends ActionSupport {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final long serialVersionUID = 1L;
    private List<User> users;

    @Autowired
    private ServiceFacade userService;

    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
        //users = (List<User>) userService.doService("get");


        RestTemplate restTemplate = new RestTemplate();
/*
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        System.out.println(quote.toString());
        log.info(quote.toString());
*/

        ResponseEntity<List<Student>> studentResponse =
                restTemplate.exchange("http://localhost:8082/user/students",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });
        List<Student> rates = studentResponse.getBody();
        log.info("get: " + rates.get(1).getSname());
        System.out.println("get: " + rates.get(1).getSname());


        return SUCCESS;
    }


    public List<User> getUsers(){
        return users;
    }
}
