package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.FacultyDepartmentsDao;
import org.dselent.scheduling.server.extractor.FacultyDepartmentsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.FacultyDepartment;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
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
public class FacultyDepartmentsDaoImpl extends BaseDaoImpl<FacultyDepartment> implements FacultyDepartmentsDao
{
    @Override
    protected String getTableName(){ return FacultyDepartment.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return FacultyDepartment.getColumnName(FacultyDepartment.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return FacultyDepartment.getColumnNameList(); }

    @Override
    public List<FacultyDepartment> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        FacultyDepartmentsExtractor extractor = new FacultyDepartmentsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<FacultyDepartment> facultyDepartmentsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return facultyDepartmentsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, FacultyDepartment facultyDepartmentModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.ID)))
        {
            parameters.addValue(parameterName, facultyDepartmentModel.getId());
        }
        else if(insertColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.FACULTY_ID)))
        {
            parameters.addValue(parameterName, facultyDepartmentModel.getFacultyID());
        }
        else if(insertColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.DEPARTMENTS_ID)))
        {
            parameters.addValue(parameterName, facultyDepartmentModel.getDepartmentsID());
        }
        else if(insertColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, facultyDepartmentModel.getCreatedAt());
        }
        else if(insertColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, facultyDepartmentModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, FacultyDepartment facultyDepartmentModel)
    {
        if(keyHolderColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.ID)))
        {
            facultyDepartmentModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.FACULTY_ID)))
        {
            facultyDepartmentModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.DEPARTMENTS_ID)))
        {
            facultyDepartmentModel.setDepartmentsID((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.CREATED_AT)))
        {
            facultyDepartmentModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(FacultyDepartment.getColumnName(FacultyDepartment.Columns.UPDATED_AT)))
        {
            facultyDepartmentModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
