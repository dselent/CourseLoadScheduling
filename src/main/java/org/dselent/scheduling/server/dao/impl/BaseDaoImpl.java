package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.Dao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Model;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for all Daos.  Contains the database connection objects and implements Dao.
 * Subclasses will specify the implementations for the functions in the Dao interface (how to get the data).
 * 
 * @author dselent
 *
 * @param <M> The model for the Dao
 */
public abstract class BaseDaoImpl<M extends Model> implements Dao<M>
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	// Forces subclasses to implement, required for insert, delete, update, findById, and validateColumnNames to work
	protected abstract String getTableName();
	protected abstract String getIdColumnName();
	protected abstract List<String> getColumnNameList();

	protected abstract void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, M model);
	protected abstract void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, M model);

	/*
	 These can be shared amongst all the DAO's so it is implemented here
	 Replaced Model specific references with M or model
	 	User -> M
	 	userModel -> model

	 This is where getTableName() becomes necessary. The only unique attribute of all these methods is the TABLE_NAME
	 So instead of making that one small edit in each DaoImpl, we just call the method getTableName()
	 Since getTableName() is overridden by each DaoImpl, it returns the correct result according the the Model

	 The same concept applies to addObjectValue and addParameterMapValue
	*/

	public int insert(M model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(getTableName(), insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList)
		{
			addParameterMapValue(parameters, insertColumnName, model);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList)
		{
			addObjectValue(keyMap, keyHolderColumnName, model);
		}

		return rowsAffected;
	}

	public int delete(List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateDeleteString(getTableName(), queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(getTableName(), columnName, queryTermList);

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

	public M findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(getIdColumnName(), false);
		List<String> selectColumnNames = getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<M> modelsList = select(selectColumnNames, queryTermList, orderByList);

		M model = null;

		if(!modelsList.isEmpty())
		{
			model = modelsList.get(0);
		}

		return model;
	}

	public void validateColumnNames(List<String> columnNameList)
	{
		List<String> actualColumnNames = getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}

}
