package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.*;
import org.dselent.scheduling.server.dto.FacultyAddDto;
import org.dselent.scheduling.server.dto.FacultyModifyDto;
import org.dselent.scheduling.server.dto.FacultyRemoveDto;
import org.dselent.scheduling.server.model.Faculty;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 *
 * @author dselent
 *
 */
@Service
public interface FacultyService
{

    public List<String> AddFaculty(FacultyAddDto facultyAddDto) throws SQLException;
    public Faculty ModifyFaculty(FacultyModifyDto facultyModifyDto) throws SQLException;
    public Faculty RemoveFaculty(FacultyRemoveDto facultyRemoveDto) throws SQLException;
}
