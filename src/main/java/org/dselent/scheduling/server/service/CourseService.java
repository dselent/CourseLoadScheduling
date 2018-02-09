
/**
 * Created by Nathan on 2/9/2018.
 */

package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CourseAddDto;
import org.dselent.scheduling.server.dto.CourseModifyDto;
import org.dselent.scheduling.server.dto.CourseRemoveDto;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 *
 * @author dselent
 *
 */
@Service
public interface CourseService {

    public List<Integer> addCourse(CourseAddDto courseAddDto) throws  SQLException;
    public List<Integer> modifyCourse(CourseModifyDto courseModifyDto) throws SQLException;
    public List<Integer> removeCourse(CourseRemoveDto courseRemoveDto) throws  SQLException;
}
