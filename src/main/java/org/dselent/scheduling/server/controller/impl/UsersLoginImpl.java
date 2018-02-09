package org.dselent.scheduling.server.controller.impl;

import org.dselent.scheduling.server.controller.UsersLogin;
import org.dselent.scheduling.server.dto.UserLoginDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.UserLogin;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UsersLoginImpl implements UsersLogin{

    @Autowired
    private UserService userService;

    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception
    {
        // Print is for testing purposes
        System.out.println("Users controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        String userName = request.get(UserLogin.getBodyName(UserLogin.BodyKey.USER_NAME));
        String password = request.get(UserLogin.getBodyName(UserLogin.BodyKey.PASSWORD));

        UserLoginDto.Builder builder = UserLoginDto.builder();
        UserLoginDto userLoginDto = builder.withUserName(userName)
                .withPassword(password)
                .build();

        userService.loginUser(userLoginDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK); // We will have to return some info about the user, like access permissions
    }
}
