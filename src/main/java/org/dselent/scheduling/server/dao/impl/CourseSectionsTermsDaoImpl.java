package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseSectionsTermsDao;
import org.dselent.scheduling.server.extractor.CourseSectionsTermsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseSectionTerm;
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
public class CourseSectionsTermsDaoImpl extends BaseDaoImpl<CourseSectionTerm> implements CourseSectionsTermsDao
{
    @Override
    protected String getTableName(){ return CourseSectionTerm.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return CourseSectionTerm.getColumnNameList(); }

    @Override
    public List<CourseSectionTerm> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        CourseSectionsTermsExtractor extractor = new CourseSectionsTermsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CourseSectionTerm> courseSectionsTermsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return courseSectionsTermsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseSectionTerm courseSectionTermModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.ID)))
        {
            parameters.addValue(parameterName, courseSectionTermModel.getId());
        }
        else if(insertColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.COURSE_SECTIONS_ID)))
        {
            parameters.addValue(parameterName, courseSectionTermModel.getCourseSectionsId());
        }
        else if(insertColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.TERMS_ID)))
        {
            parameters.addValue(parameterName, courseSectionTermModel.getTermsId());
        }
        else if(insertColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, courseSectionTermModel.getCreatedAt());
        }
        else if(insertColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, courseSectionTermModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseSectionTerm courseSectionTermModel)
    {
        if(keyHolderColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.ID)))
        {
            courseSectionTermModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.COURSE_SECTIONS_ID)))
        {
            courseSectionTermModel.setCourseSectionsId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.TERMS_ID)))
        {
            courseSectionTermModel.setTermsId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.CREATED_AT)))
        {
            courseSectionTermModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.UPDATED_AT)))
        {
            courseSectionTermModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
