package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.dto.LocationAddDto;
import org.dselent.scheduling.server.model.Location;
import org.dselent.scheduling.server.requests.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public interface  LocationController {
    @RequestMapping(method= RequestMethod.POST, value= LocationAdd)
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value= LocationModify)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value= LocationRemove)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
}
