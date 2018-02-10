package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.CourseDepartmentController;
import org.dselent.scheduling.server.dto.CourseDepartmentAddDto;
import org.dselent.scheduling.server.dto.CourseDepartmentModifyDto;
import org.dselent.scheduling.server.dto.CourseDepartmentRemoveDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CourseDepartmentAdd;
import org.dselent.scheduling.server.requests.CourseDepartmentModify;
import org.dselent.scheduling.server.requests.CourseDepartmentRemove;
import org.dselent.scheduling.server.service.CourseDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 *
 * @author dselent
 */

@Controller
public class CourseDepartmentControllerImpl implements CourseDepartmentController
{
    @Autowired
    private CourseDepartmentService courseDepartmentService;

    /**
     *
     * @param request The body of the request expected to contain a map of String key-value pairs
     * @return A ResponseEntity for the response object(s) and the status code
     * @throws Exception
     */
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception
    {
        // Print is for testing purposes
        System.out.println("Users controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        String userName = request.get(UserRegister.getBodyName(UserRegister.BodyKey.USER_NAME));
        String firstName = request.get(UserRegister.getBodyName(UserRegister.BodyKey.FIRST_NAME));
        String lastName = request.get(UserRegister.getBodyName(UserRegister.BodyKey.LAST_NAME));
        String email = request.get(UserRegister.getBodyName(UserRegister.BodyKey.EMAIL));
        String password = request.get(UserRegister.getBodyName(UserRegister.BodyKey.PASSWORD));

        UserRegisterDto.Builder builder = UserRegisterDto.builder();
        UserRegisterDto userRegisterDto = builder.withUserName(userName)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .build();

        userService.registerUser(userRegisterDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

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