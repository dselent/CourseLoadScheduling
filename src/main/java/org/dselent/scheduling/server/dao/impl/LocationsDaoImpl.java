package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.LocationsDao;
import org.dselent.scheduling.server.extractor.LocationsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Location;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class LocationsDaoImpl extends BaseDaoImpl<Location> implements LocationsDao {

    @Override
    public List<Location> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException{
        LocationsExtractor extractor = new LocationsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Location.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<>();

        for(QueryTerm queryTerm : queryTermList){
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Location> locationsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return locationsList;
    }

    @Override
    public Location findById(int id) throws SQLException{
        String columnName = QueryStringBuilder.convertColumnName(Location.getColumnName(Location.Columns.ID), false);
        List<String> selectColumnNames = Location.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Location> locationsList = select(selectColumnNames, queryTermList, orderByList);

        Location location = null;

        if(!locationsList.isEmpty()){
            location = locationsList.get(0);
        }

        return location;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(Location.TABLE_NAME, columnName, queryTermList);

        List<Object> objectList = new ArrayList<>();
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
        String queryTemplate = QueryStringBuilder.generateDeleteString(Location.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Location locationModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(Location.getColumnName(Location.Columns.ID)))
        {
            parameters.addValue(parameterName, locationModel.getId());
        }
        else if(insertColumnName.equals(Location.getColumnName(Location.Columns.BUILDING)))
        {
            parameters.addValue(parameterName, locationModel.getBuilding());
        }
        else if(insertColumnName.equals(Location.getColumnName(Location.Columns.ROOM))){
            parameters.addValue(parameterName, locationModel.getRoom());
        }
        else if(insertColumnName.equals(Location.getColumnName(Location.Columns.ROOM_SIZE))){
            parameters.addValue(parameterName, locationModel.getRoomSize());
        }
        else if(insertColumnName.equals(Location.getColumnName(Location.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, locationModel.getCreatedAt());
        }
        else if(insertColumnName.equals(Location.getColumnName(Location.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, locationModel.getUpdatedAt());
        }
        else {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Location locationModel)
    {
        if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.ID)))
        {
            locationModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.BUILDING))){
            locationModel.setBuilding((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.ROOM))){
            locationModel.setRoom((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.ROOM_SIZE))){
            locationModel.setRoomSize((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.CREATED_AT)))
        {
            locationModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Location.getColumnName(Location.Columns.UPDATED_AT)))
        {
            locationModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

    @Override
    public void validateColumnNames(List<String> columnNameList){
        List<String> actualColumnNames = Location.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid){
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}
