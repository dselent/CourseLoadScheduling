package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dto.CourseAddDto;
import org.dselent.scheduling.server.dto.CourseModifyDto;
import org.dselent.scheduling.server.dto.CourseRemoveDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.dselent.scheduling.server.sqlutils.ComparisonOperator.EQUAL;
/**
 * Created by Nathan on 2/9/2018.
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CoursesDao coursesDao;

    public CourseServiceImpl(){

    }

    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.CourseService#addCourse(org.dselent.scheduling.server.dto.CourseAddDto)
     */
    @Transactional
    @Override
    public List<Integer> addCourse(CourseAddDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setCourseDescription(dto.getCourseName());

        List<String> courseInsertColumnNameList = new ArrayList<>();
        List<String> courseKeyHolderColumnNameList = new ArrayList<>();

        courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.COURSE_NAME));
        courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.COURSE_DESCRIPTION));

        courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
        courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
        courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));

        rowsAffectedList.add(coursesDao.insert(course, courseInsertColumnNameList, courseKeyHolderColumnNameList));
        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> modifyCourse(CourseModifyDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseId = dto.getCourseId();
        String courseName = dto.getCourseName();
        String courseDescription = dto.getCourseDescription();

        /*I have no idea how to modify a course's department. the system'll work okay without it though*/
        String courseDept = dto.getCourseDept();


        queryTermList.add(new QueryTerm(Course.getColumnName(Course.Columns.ID),EQUAL,courseId,null));

        rowsAffectedList.add(coursesDao.update(Course.getColumnName(Course.Columns.COURSE_NAME),courseName,queryTermList));
        rowsAffectedList.add(coursesDao.update(Course.getColumnName(Course.Columns.COURSE_DESCRIPTION),courseDescription,queryTermList));

        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> removeCourse(CourseRemoveDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseId = dto.getCourseId();
        queryTermList.add(new QueryTerm(Course.getColumnName(Course.Columns.ID),EQUAL,courseId,null));

        rowsAffectedList.add(coursesDao.delete(queryTermList));

        return rowsAffectedList;
    }
}
