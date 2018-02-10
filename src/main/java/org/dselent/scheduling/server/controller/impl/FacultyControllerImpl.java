package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.FacultyController;
import org.dselent.scheduling.server.dto.*;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.*;
import org.dselent.scheduling.server.service.FacultyService;
import org.dselent.scheduling.server.service.FacultyService;
import org.dselent.scheduling.server.service.UserService;
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
public class FacultyControllerImpl implements FacultyController
{
    @Autowired
    private FacultyService facultyService;

    /**
     *
     * @param request The body of the request expected to contain a map of String key-value pairs
     * @return A ResponseEntity for the response object(s) and the status code
     * @throws Exception
     */
    public ResponseEntity<String> add(@RequestBody Map<String, String> request) throws Exception
    {
        // Print is for testing purposes
        System.out.println("Faculty controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer UserId = Integer.parseInt(request.get(FacultyAdd.getBodyName(FacultyAdd.BodyKey.USER_ID)));
        Integer RequiredCredits = Integer.parseInt(request.get(FacultyAdd.getBodyName(FacultyAdd.BodyKey.REQUIRED_CREDITS)));

        FacultyAddDto.Builder builder = FacultyAddDto.builder();
        FacultyAddDto facultyAddDto = builder.withUserId(UserId)
                .withRequiredCredits(RequiredCredits)
                .build();


        facultyService.addFaculty(facultyAddDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> modify(@RequestBody Map<String, String> request) throws Exception
    {
        // Print is for testing purposes
        System.out.println("Faculty controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer FacultyId = Integer.parseInt(request.get(FacultyModify.getBodyName(FacultyModify.BodyKey.FACULTY_ID)));
        Integer UserId = Integer.parseInt(request.get(FacultyModify.getBodyName(FacultyModify.BodyKey.USER_ID)));
        Integer RequiredCredits = Integer.parseInt(request.get(FacultyModify.getBodyName(FacultyModify.BodyKey.REQUIRED_CREDITS)));

        FacultyModifyDto.Builder builder = FacultyModifyDto.builder();
        FacultyModifyDto facultyModifyDto = builder.withUserId(UserId)
                .withRequiredCredits(RequiredCredits)
                .withFacultyId(FacultyId)
                .build();

        facultyService.modifyFaculty(facultyModifyDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK); // We will have to return some info about the user, like access permissions
    }

    public ResponseEntity<String> remove(@RequestBody Map<String, String> request) throws Exception
    {
        // Print is for testing purposes
        System.out.println("Faculty controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer facultyId = Integer.parseInt(request.get(FacultyRemove.getBodyName(FacultyRemove.BodyKey.FACULTY_ID)));

        FacultyRemoveDto.Builder builder = FacultyRemoveDto.builder();
        FacultyRemoveDto facultyRemoveDto = builder.withFacultyId(facultyId)
                .build();

        facultyService.removeFaculty(facultyRemoveDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK); // We will have to return some info about the user, like access permissions
    }
}

