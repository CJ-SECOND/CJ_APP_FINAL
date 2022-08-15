package com.backend.app.business.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backend.app.business.user.service.UserService;
import com.backend.app.model.Payload;

@Controller
@RequestMapping("/user")
public class UserController{
    
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Payload> login(@RequestBody Payload request) {
       Payload result = new Payload();     
       result = userService.login(request);   
       return ResponseEntity.ok(result);
    }
    
    @ResponseBody
    @PostMapping("/registerUser")
    public ResponseEntity<Payload> registerUser(@RequestBody Payload request) {
    	Payload result = new Payload();
    	result = userService.registerUser(request);	
    	return ResponseEntity.ok(result);
    }
    
    @ResponseBody
    @GetMapping("/getUser")
    public ResponseEntity<Payload> getUser() {
    	Payload result = new Payload();
    	    	
    	Payload request = new Payload();
    	request.set("userSeq", 1);
    	result = userService.selectUserName(request);	
    	return ResponseEntity.ok(result);
    }
}
