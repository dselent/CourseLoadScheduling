package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UsersPermissionsDao;
import org.dselent.scheduling.server.extractor.UsersPermissionsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UserPermission;

import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;


/**
 * Created by Nathan on 2/2/2018.
 */
@Repository
public class UsersPermissionsDaoImpl extends BaseDaoImpl<UserPermission> implements UsersPermissionsDao{

    @Override
    protected String getTableName(){ return UserPermission.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return UserPermission.getColumnName(UserPermission.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return UserPermission.getColumnNameList(); }

    @Override
    public List<UserPermission> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        UsersPermissionsExtractor extractor = new UsersPermissionsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<UserPermission> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return usersList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UserPermission userModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.ID)))
        {
            parameters.addValue(parameterName, userModel.getId());
        }
        else if(insertColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.USERS_ID)))
        {
            parameters.addValue(parameterName, userModel.getUsersId());
        }
        else if(insertColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.ROLE)))
        {
            parameters.addValue(parameterName, userModel.getRole());
        }
        else if(insertColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, userModel.getCreatedAt());
        }
        else if(insertColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.UPDATED_AT)))
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
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, UserPermission userModel)
    {
        if(keyHolderColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.ID)))
        {
            userModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.USERS_ID)))
        {
            userModel.setUsersId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.ROLE)))
        {
            userModel.setRole((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.CREATED_AT)))
        {
            userModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(UserPermission.getColumnName(UserPermission.Columns.UPDATED_AT)))
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
