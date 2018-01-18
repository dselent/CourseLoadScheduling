package org.dselent.scheduling.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.requests.Register;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
@RequestMapping("/user")
public class UsersController
{
	@Autowired
    private UserService userService;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 */
    @RequestMapping(method=RequestMethod.POST, value=Register.REQUEST_NAME)
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		String response = "";
		List<Object> success = new ArrayList<Object>();
		List<Object> error = new ArrayList<Object>();
		
		String userName = request.get(Register.getBodyName(Register.BodyKey.USER_NAME));
		String firstName = request.get(Register.getBodyName(Register.BodyKey.FIRST_NAME));
		String lastName = request.get(Register.getBodyName(Register.BodyKey.LAST_NAME));
		String email = request.get(Register.getBodyName(Register.BodyKey.EMAIL));
		String password = request.get(Register.getBodyName(Register.BodyKey.PASSWORD));

		RegisterUserDto.Builder builder = RegisterUserDto.builder();
		RegisterUserDto registerUserDto = builder.withUserName(userName)
		.withFirstName(firstName)
		.withLastName(lastName)
		.withEmail(email)
		.withPassword(password)
		.build();
		
		try
		{
			userService.registerUser(registerUserDto);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			response = JsonResponseCreator.getJSONResponse(success, error);
		}
		catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}

	