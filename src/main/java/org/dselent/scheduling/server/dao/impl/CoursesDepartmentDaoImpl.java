package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesDepartmentDao;
import org.dselent.scheduling.server.extractor.CourseDepartmentsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;

import org.dselent.scheduling.server.model.CourseDepartment;

import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

public abstract class CoursesDepartmentDaoImpl extends BaseDaoImpl<CourseDepartment> implements CoursesDepartmentDao {
    @Override

    public int insert(CourseDepartment courseDepartmentModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(CourseDepartment.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for (String insertColumnName : insertColumnNameList) {
            addParameterMapValue(parameters, insertColumnName, courseDepartmentModel);
        }
        // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
        // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for (String keyHolderColumnName : keyHolderColumnNameList) {
            addObjectValue(keyMap, keyHolderColumnName, courseDepartmentModel);
        }

        return rowsAffected;

    }


    @Override
    public List<CourseDepartment> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
        CourseDepartmentsExtractor extractor = new CourseDepartmentsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(CourseDepartment.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for (QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CourseDepartment> courseDepartmentList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return courseDepartmentList;
    }

    @Override
    public CourseDepartment findById(int id) throws SQLException {
        String columnName = QueryStringBuilder.convertColumnName(CourseDepartment.getColumnName(CourseDepartment.Columns.ID), false);
        List<String> selectColumnNames = CourseDepartment.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<CourseDepartment> courseDepartmentList = select(selectColumnNames, queryTermList, orderByList);

        CourseDepartment courseDepartment = null;

        if (!courseDepartmentList.isEmpty()) {
            courseDepartment = courseDepartmentList.get(0);
        }

        return courseDepartment;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateUpdateString(CourseDepartment.TABLE_NAME, columnName, queryTermList);

        List<Object> objectList = new ArrayList<Object>();
        objectList.add(newValue);

        for (QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    @Override
    public int delete(List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateDeleteString(CourseDepartment.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for (QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseDepartment courseModel) {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.ID)))
        {
            parameters.addValue(parameterName, courseModel.getId());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_ID)))
        {
            parameters.addValue(parameterName, courseModel.getCourseId());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENT_ID)))
        {
            parameters.addValue(parameterName, courseModel.getDepartmentId());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_NUMBER)))
        {
            parameters.addValue(parameterName, courseModel.getCourseNumber());

        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, courseModel.getCreatedAt());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, courseModel.getUpdatedAt());

        } else {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseDepartment courseModel) {
        if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.ID)))
        {
            courseModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENT_ID)))
        {
            courseModel.setDepartmentId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_NUMBER)))
        {
            courseModel.setCourseNumber((Integer) keyMap.get(keyHolderColumnName));
        }
        else if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.CREATED_AT)))
        {
            courseModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.UPDATED_AT)))
        {
            courseModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));

        } else {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

    @Override
    public void validateColumnNames(List<String> columnNameList) {
        List<String> actualColumnNames = CourseDepartment.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if (!valid) {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}