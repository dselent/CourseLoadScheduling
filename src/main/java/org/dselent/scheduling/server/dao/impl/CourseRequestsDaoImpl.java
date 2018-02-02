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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public int insert(CourseRequest courseRequestModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(CourseRequest.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
            addParameterMapValue(parameters, insertColumnName, courseRequestModel);
        }
        // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
        // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, courseRequestModel));

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, courseRequestModel);
        }

        return rowsAffected;

    }


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
    public CourseRequest findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(CourseRequest.getColumnName(CourseRequest.Columns.ID), false);
        List<String> selectColumnNames = CourseRequest.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<CourseRequest> courseRequestsList = select(selectColumnNames, queryTermList, orderByList);

        CourseRequest courseRequest = null;

        if(!courseRequestsList.isEmpty())
        {
            courseRequest = courseRequestsList.get(0);
        }

        return courseRequest;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(CourseRequest.TABLE_NAME, columnName, queryTermList);

        List<Object> objectList = new ArrayList<Object>();
        objectList.add(newValue);

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    @Override
    public int delete(List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateDeleteString(CourseRequest.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseRequest courseRequestModel)
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

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseRequest courseRequestModel)
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

    @Override
    public void validateColumnNames(List<String> columnNameList)
    {
        List<String> actualColumnNames = CourseRequest.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}
