package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.UserLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user/login")
public interface UsersLogin
{

    @RequestMapping(method=RequestMethod.POST, value=UserLogin.REQUEST_NAME)

    // Logs a User into the system
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
}
