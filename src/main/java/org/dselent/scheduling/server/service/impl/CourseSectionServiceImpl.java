package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseSectionsDao;
import org.dselent.scheduling.server.dto.CourseSectionAddDto;
import org.dselent.scheduling.server.dto.CourseSectionModifyDto;
import org.dselent.scheduling.server.dto.CourseSectionRemoveDto;
import org.dselent.scheduling.server.model.CourseSection;
import org.dselent.scheduling.server.service.CourseSectionService;

import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.dselent.scheduling.server.sqlutils.ComparisonOperator.EQUAL;


/**
 * Created by Nathan on 2/9/2018.
 */
@Service
public class CourseSectionServiceImpl implements CourseSectionService{
    @Autowired
    private CourseSectionsDao courseSectionsDao;

    public CourseSectionServiceImpl(){

    }
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.CourseSectionService#addCourseSection(org.dselent.scheduling.server.dto.CourseSectionAddDto)
     */
    @Transactional
    @Override
    public List<Integer> addCourseSection(CourseSectionAddDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        CourseSection courseSection = new CourseSection();
        courseSection.setCoursesId(dto.getCourseId());
        courseSection.setSectionType(dto.getSectionType());

        /*need to figure out how to do this as well */
        String term = dto.getTerm();

        List<String> courseInsertColumnNameList = new ArrayList<>();
        List<String> courseKeyHolderColumnNameList = new ArrayList<>();

        courseInsertColumnNameList.add(CourseSection.getColumnName(CourseSection.Columns.COURSES_ID));
        courseInsertColumnNameList.add(CourseSection.getColumnName(CourseSection.Columns.SECTION_TYPE));

        courseKeyHolderColumnNameList.add(CourseSection.getColumnName(CourseSection.Columns.ID));
        courseKeyHolderColumnNameList.add(CourseSection.getColumnName(CourseSection.Columns.CREATED_AT));
        courseKeyHolderColumnNameList.add(CourseSection.getColumnName(CourseSection.Columns.UPDATED_AT));


        rowsAffectedList.add(courseSectionsDao.insert(courseSection, courseInsertColumnNameList, courseKeyHolderColumnNameList));

        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> modifyCourseSection(CourseSectionModifyDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseSectionId = dto.getCourseSectionId();
        Integer courseId = dto.getCourseId();
        String courseSectionType = dto.getSectionType();

        /*I have no idea how to modify a course section's term. */
        String courseTerm = dto.getTerm();

        queryTermList.add(new QueryTerm(CourseSection.getColumnName(CourseSection.Columns.ID),EQUAL,courseSectionId,null));

        rowsAffectedList.add(courseSectionsDao.update(CourseSection.getColumnName((CourseSection.Columns.COURSES_ID)),courseId,queryTermList));
        rowsAffectedList.add(courseSectionsDao.update(CourseSection.getColumnName((CourseSection.Columns.SECTION_TYPE)),courseSectionType,queryTermList));

        return rowsAffectedList;

    }

    @Transactional
    @Override
    public List<Integer> removeCourseSection(CourseSectionRemoveDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer courseSectionId = dto.getCourseSectionId();
        queryTermList.add(new QueryTerm(CourseSection.getColumnName(CourseSection.Columns.ID),EQUAL,courseSectionId, null));

        rowsAffectedList.add(courseSectionsDao.delete(queryTermList));

        return rowsAffectedList;

    }
}
