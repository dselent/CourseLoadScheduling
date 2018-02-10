package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.UserLogin;
import org.dselent.scheduling.server.requests.UserLogout;
import org.dselent.scheduling.server.requests.UserModify;
import org.dselent.scheduling.server.requests.UserRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
public interface UsersController
{
    // Registers a user in the system
    @RequestMapping(method=RequestMethod.POST, value= UserRegister.REQUEST_NAME)
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception;

    // Logs a User into the system
    @RequestMapping(method=RequestMethod.POST, value= UserLogin.REQUEST_NAME)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;

    //Logs a User out of the system
    @RequestMapping(method=RequestMethod.POST, value= UserLogout.REQUEST_NAME)
    public ResponseEntity<String> logout(@RequestBody Map<String,String> request) throws Exception;

    //Modifies a User
    @RequestMapping(method=RequestMethod.POST, value= UserModify.REQUEST_NAME)
    public ResponseEntity<String> modify(@RequestBody Map<String, String> request) throws Exception;

}

	