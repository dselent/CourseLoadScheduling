package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesDepartmentDao;
import org.dselent.scheduling.server.extractor.CourseDepartmentsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseDepartment;

import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CoursesDepartmentDaoImpl extends BaseDaoImpl<CourseDepartment> implements CoursesDepartmentDao {

    @Override
    protected String getTableName(){ return CourseDepartment.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return CourseDepartment.getColumnName(CourseDepartment.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return CourseDepartment.getColumnNameList(); }

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
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseDepartment courseModel) {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.ID)))
        {
            parameters.addValue(parameterName, courseModel.getId());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSES_ID)))
        {
            parameters.addValue(parameterName, courseModel.getCourseId());
        }
        else if (insertColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENTS_ID)))
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

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseDepartment courseModel) {
        if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.ID)))
        {
            courseModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if (keyHolderColumnName.equals(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENTS_ID)))
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

}