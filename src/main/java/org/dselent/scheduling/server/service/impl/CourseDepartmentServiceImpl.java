package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.CoursesDepartmentDao;
import org.dselent.scheduling.server.dto.CourseDepartmentAddDto;
import org.dselent.scheduling.server.dto.CourseDepartmentModifyDto;
import org.dselent.scheduling.server.dto.CourseDepartmentRemoveDto;
import org.dselent.scheduling.server.model.CourseDepartment;
import org.dselent.scheduling.server.service.CourseDepartmentService;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.dselent.scheduling.server.sqlutils.ComparisonOperator.EQUAL;
/**
 * Created by Nathan on 2/9/2018.
 */
@Service
public class CourseDepartmentServiceImpl implements CourseDepartmentService{
    @Autowired
    private CoursesDepartmentDao coursesDepartmentDao;

    public CourseDepartmentServiceImpl(){

    }

    @Transactional
    @Override
    public List<Integer> addCourseDepartment(CourseDepartmentAddDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        CourseDepartment courseDepartment = new CourseDepartment();
        courseDepartment.setCourseId(dto.getCourseId());
        courseDepartment.setDepartmentId(dto.getDepartmentId());
        courseDepartment.setCourseNumber(dto.getCourseNumber());

        List<String> courseInsertColumnNameList = new ArrayList<>();
        List<String> courseKeyHolderColumnNameList = new ArrayList<>();

        courseInsertColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSES_ID));
        courseInsertColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENTS_ID));
        courseInsertColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_NUMBER));

        courseKeyHolderColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.ID));
        courseKeyHolderColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.CREATED_AT));
        courseKeyHolderColumnNameList.add(CourseDepartment.getColumnName(CourseDepartment.Columns.UPDATED_AT));

        rowsAffectedList.add(coursesDepartmentDao.insert(courseDepartment, courseInsertColumnNameList, courseKeyHolderColumnNameList));

        return rowsAffectedList;

    }
    @Transactional
    @Override
    public List<Integer> modifyCourseDepartment(CourseDepartmentModifyDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseDepartmentId = dto.getCourseDepartmentId();
        Integer courseId = dto.getCourseId();
        Integer departmentId = dto.getDepartmentId();
        Integer setCourseNumber = dto.getCourseNumber();

        queryTermList.add(new QueryTerm(CourseDepartment.getColumnName(CourseDepartment.Columns.ID), EQUAL,courseDepartmentId,null));

        rowsAffectedList.add(coursesDepartmentDao.update(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSES_ID),courseId,queryTermList));
        rowsAffectedList.add(coursesDepartmentDao.update(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENTS_ID),departmentId,queryTermList));
        rowsAffectedList.add(coursesDepartmentDao.update(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_NUMBER),setCourseNumber,queryTermList));

        return rowsAffectedList;
    }
    @Transactional
    @Override
    public List<Integer> removeCourseDepartment(CourseDepartmentRemoveDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseDepartmentId = dto.getCourseDepartmentId();
        queryTermList.add(new QueryTerm(CourseDepartment.getColumnName(CourseDepartment.Columns.ID),EQUAL,courseDepartmentId,null));

        rowsAffectedList.add(coursesDepartmentDao.delete(queryTermList));

        return rowsAffectedList;

    }
}
