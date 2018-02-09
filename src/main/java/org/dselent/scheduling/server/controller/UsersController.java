package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.UserRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user/register")
public interface UsersController
{
    
    @RequestMapping(method=RequestMethod.POST, value=UserRegister.REQUEST_NAME)

    // Registers a user in the system
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception;
}

	