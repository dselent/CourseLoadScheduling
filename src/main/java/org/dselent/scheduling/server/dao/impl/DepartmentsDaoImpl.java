package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.DepartmentsDao;
import org.dselent.scheduling.server.extractor.DepartmentsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Department;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.stereotype.Repository;

@Repository
public class DepartmentsDaoImpl extends BaseDaoImpl<Department> implements DepartmentsDao {


    @Override
    protected String getTableName(){ return Department.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return Department.getColumnName(Department.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return Department.getColumnNameList(); }

    @Override
    public List<Department> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        DepartmentsExtractor extractor = new DepartmentsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Department> departmentsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return departmentsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Department DepartmentModel) {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if (insertColumnName.equals(Department.getColumnName(Department.Columns.ID))) {
            parameters.addValue(parameterName, DepartmentModel.getId());
        } else if (insertColumnName.equals(Department.getColumnName(Department.Columns.DEPARTMENT))) {
            parameters.addValue(parameterName, DepartmentModel.getDepartment());
        } else if (insertColumnName.equals(Department.getColumnName(Department.Columns.CREATED_AT))) {
            parameters.addValue(parameterName, DepartmentModel.getCreatedAt());
        } else if (insertColumnName.equals(Department.getColumnName(Department.Columns.UPDATED_AT))) {
            parameters.addValue(parameterName, DepartmentModel.getUpdatedAt());

        } else {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Department DepartmentModel) {
        if (keyHolderColumnName.equals(Department.getColumnName(Department.Columns.ID))) {
            DepartmentModel.setId((Integer) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Department.getColumnName(Department.Columns.DEPARTMENT))) {
            DepartmentModel.setDepartment((String) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Department.getColumnName(Department.Columns.CREATED_AT))) {
            DepartmentModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Department.getColumnName(Department.Columns.UPDATED_AT))) {
            DepartmentModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));

        } else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
