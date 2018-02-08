package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.ScheduleController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.UserRegister;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScheduleControllerImpl implements ScheduleController{

    @Autowired
    private ScheduleService scheduleService; // Not sure what this is?


    @Override
    public ResponseEntity<String> getSchedules(@RequestBody Map<String, String> request) throws Exception {

        System.out.println("Schedule Controller Reached!"); // Debugging

        String response = "";
        List<Object> success = new ArrayList<>();

        // Add return info, like a course section



        // Modify this to return the info?
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
