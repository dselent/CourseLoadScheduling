package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dto.FacultyAddDto;
import org.dselent.scheduling.server.dto.FacultyModifyDto;
import org.dselent.scheduling.server.dto.FacultyRemoveDto;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.service.FacultyService;

import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.dselent.scheduling.server.sqlutils.ComparisonOperator.EQUAL;
/**
 * Created by Fabian Gaziano on 2/9/2018.
 */
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyDao FacultyDao;

    public FacultyServiceImpl(){

    }

    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.CourseService#addCourse(org.dselent.scheduling.server.dto.CourseAddDto)
     */
    @Transactional
    @Override
    public List<Integer> addFaculty(FacultyAddDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        Faculty faculty = new Faculty();
        faculty.setUsersId(dto.getUserId());
        faculty.setRequiredCredits(dto.getRequiredCredits());



        List<String> facultyInsertColumnNameList = new ArrayList<>();
        List<String> facultyKeyHolderColumnNameList = new ArrayList<>();

        facultyInsertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.USERS_ID));
        facultyInsertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.REQUIRED_CREDITS));
        facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ID));
        facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.CREATED_AT));
        facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.UPDATED_AT));

        rowsAffectedList.add(FacultyDao.insert(faculty, facultyInsertColumnNameList, facultyKeyHolderColumnNameList));
        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> modifyCourse(FacultyModifyDto dto) throws SQLException{
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
