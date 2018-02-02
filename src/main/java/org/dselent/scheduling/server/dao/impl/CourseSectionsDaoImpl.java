package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseSectionsDao;
import org.dselent.scheduling.server.extractor.CourseSectionsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseSection;
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
public class CourseSectionsDaoImpl extends BaseDaoImpl<CourseSection> implements CourseSectionsDao
{

    @Override
    protected String getTableName(){ return CourseSection.TABLE_NAME; }

    @Override
    protected String getIdColumnName(){ return CourseSection.getColumnName(CourseSection.Columns.ID); }

    @Override
    protected List<String> getColumnNameList(){ return CourseSection.getColumnNameList(); }

    @Override
    public List<CourseSection> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        CourseSectionsExtractor extractor = new CourseSectionsExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(getTableName(), selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CourseSection> CourseSectionsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return CourseSectionsList;
    }

    @Override
    protected void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseSection courseSectionModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.ID)))
        {
            parameters.addValue(parameterName, courseSectionModel.getId());
        }
        else if(insertColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.COURSES_ID)))
        {
            parameters.addValue(parameterName, courseSectionModel.getCoursesId());
        }
        else if(insertColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.SECTION_TYPE)))
        {
            parameters.addValue(parameterName, courseSectionModel.getSectionType());
        }

        else if(insertColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, courseSectionModel.getCreatedAt());
        }
        else if(insertColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, courseSectionModel.getUpdatedAt());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    @Override
    protected void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseSection courseSectionModel)
    {
        if(keyHolderColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.ID)))
        {
            courseSectionModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.COURSES_ID)))
        {
            courseSectionModel.setCoursesId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.SECTION_TYPE))) {
            courseSectionModel.setSectionType((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.CREATED_AT)))
        {
            courseSectionModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(CourseSection.getColumnName(CourseSection.Columns.UPDATED_AT)))
        {
            courseSectionModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
