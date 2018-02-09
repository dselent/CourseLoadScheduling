
package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;


import org.dselent.scheduling.server.model.Faculty;

import org.dselent.scheduling.server.dto.FacultyAddDto;
import org.dselent.scheduling.server.dto.FacultyModifyDto;
import org.dselent.scheduling.server.dto.FacultyRemoveDto;
import org.dselent.scheduling.server.dto.FacultyRequestCourseDto;
import org.dselent.scheduling.server.dto.FacultyUnrequestCourseDto;

import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 *
 * Fabian Gaziano
 *
 */
@Service
public interface FacultyService {

    public List<Integer> addFaculty(FacultyAddDto dto) throws  SQLException;
    public List<Integer> modifyFaculty(FacultyModifyDto dto) throws SQLException;
    public List<Integer> removeFaculty(FacultyRemoveDto dto) throws  SQLException;
}

