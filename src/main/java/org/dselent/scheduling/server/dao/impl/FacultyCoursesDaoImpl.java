package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.FacultyCoursesDao;
import org.dselent.scheduling.server.extractor.FacultyCoursesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.FacultyCourse;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;


/*
 * @Repository annotation
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html
 *
 * Useful link
 * https://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 */
@Repository
public class FacultyCoursesDaoImpl extends BaseDaoImpl<FacultyCourse> implements FacultyCoursesDao
{
    @Override
    protected String getTableName(){ return FacultyCourse.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return FacultyCourse.getColumnName(FacultyCourse.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return FacultyCourse.getColumnNameList(); }

    @Override
    public List<FacultyCourse> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        FacultyCoursesExtractor extractor = new FacultyCoursesExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<FacultyCourse> FacultyCoursesList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return FacultyCoursesList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, FacultyCourse userModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.ID)))
        {
            parameters.addValue(parameterName, userModel.getId());
        }
        else if(insertColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.FACULTY_ID)))
        {
            parameters.addValue(parameterName, userModel.getFacultyID());
        }
        else if(insertColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.COURSE_SECTIONS_ID)))
        {
            parameters.addValue(parameterName, userModel.getCourseSectionID());
        }
        else if(insertColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, userModel.getCreatedAt());
        }
        else if(insertColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, userModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, FacultyCourse userModel)
    {
        if(keyHolderColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.ID)))
        {
            userModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.FACULTY_ID)))
        {
            userModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.COURSE_SECTIONS_ID)))
        {
            userModel.setCourseSectionID((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.CREATED_AT)))
        {
            userModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyCourse.getColumnName(FacultyCourse.Columns.UPDATED_AT)))
        {
            userModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}

