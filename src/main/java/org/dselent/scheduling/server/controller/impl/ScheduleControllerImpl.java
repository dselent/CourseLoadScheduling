package org.dselent.scheduling.server.controller.impl;

import org.dselent.scheduling.server.controller.ScheduleController;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduleControllerImpl implements ScheduleController{
/**
    @Autowired
    private ScheduleService scheduleService; // Not sure what this is?

    /* @rob it's a file that needs to be made in the service @ service/impl folder
        -Nathan



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
    */
}
