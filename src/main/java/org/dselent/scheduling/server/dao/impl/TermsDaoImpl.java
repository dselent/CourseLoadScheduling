package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.TermsDao;
import org.dselent.scheduling.server.extractor.TermsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Term;
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
public class TermsDaoImpl extends BaseDaoImpl<Term> implements TermsDao
{

    @Override
    protected String getTableName(){ return Term.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return Term.getColumnName(Term.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return Term.getColumnNameList(); }

    @Override
    public List<Term> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        TermsExtractor extractor = new TermsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Term.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Term> termsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return termsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Term termModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(Term.getColumnName(Term.Columns.ID)))
        {
            parameters.addValue(parameterName, termModel.getId());
        }
        else if(insertColumnName.equals(Term.getColumnName(Term.Columns.TERM_NAME)))
        {
            parameters.addValue(parameterName, termModel.getTermName());
        }

        else if(insertColumnName.equals(Term.getColumnName(Term.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, termModel.getCreatedAt());
        }
        else if(insertColumnName.equals(Term.getColumnName(Term.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, termModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Term termModel)
    {
        if(keyHolderColumnName.equals(Term.getColumnName(Term.Columns.ID)))
        {
            termModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Term.getColumnName(Term.Columns.TERM_NAME)))
        {
            termModel.setTermName((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Term.getColumnName(Term.Columns.CREATED_AT)))
        {
            termModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Term.getColumnName(Term.Columns.UPDATED_AT)))
        {
            termModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
