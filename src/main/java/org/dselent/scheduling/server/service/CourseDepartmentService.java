package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CourseDepartmentAddDto;
import org.dselent.scheduling.server.dto.CourseDepartmentModifyDto;
import org.dselent.scheduling.server.dto.CourseDepartmentRemoveDto;
import org.springframework.stereotype.Service;

/**
 * Created by Nathan on 2/9/2018.
 */
@Service
public interface CourseDepartmentService {
    public List<Integer> addCourseDepartment(CourseDepartmentAddDto dto) throws SQLException;
    public List<Integer> modifyCourseDepartment(CourseDepartmentModifyDto dto) throws SQLException;
    public List<Integer> removeCourseDepartment(CourseDepartmentRemoveDto dto) throws SQLException;

}
