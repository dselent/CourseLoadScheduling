package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseRequestsDao;
import org.dselent.scheduling.server.extractor.CourseRequestsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseRequest;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
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
public abstract class CourseRequestsDaoImpl extends BaseDaoImpl<CourseRequest> implements CourseRequestsDao
{
    @Override
    protected String getTableName(){ return CourseRequest.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return CourseRequest.getColumnName(CourseRequest.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return CourseRequest.getColumnNameList(); }


    @Override
    public List<CourseRequest> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        CourseRequestsExtractor extractor = new CourseRequestsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(CourseRequest.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CourseRequest> courseRequestsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return courseRequestsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseRequest courseRequestModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.ID)))
        {
            parameters.addValue(parameterName, courseRequestModel.getId());
        }
        else if(insertColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.FACULTY_ID)))
        {
            parameters.addValue(parameterName, courseRequestModel.getFacultyId());
        }
        else if(insertColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.COURSE_SECTIONS_ID)))
        {
            parameters.addValue(parameterName, courseRequestModel.getCourseSectionsId());
        }

        else if(insertColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, courseRequestModel.getCreatedAt());
        }
        else if(insertColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, courseRequestModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseRequest courseRequestModel)
    {
        if(keyHolderColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.ID)))
        {
            courseRequestModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.FACULTY_ID)))
        {
            courseRequestModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.COURSE_SECTIONS_ID)))
        {
            courseRequestModel.setCourseSectionsId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.CREATED_AT)))
        {
            courseRequestModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseRequest.getColumnName(CourseRequest.Columns.UPDATED_AT)))
        {
            courseRequestModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
