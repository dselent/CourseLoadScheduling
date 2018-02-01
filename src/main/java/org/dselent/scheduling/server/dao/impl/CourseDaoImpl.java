package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseDao;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

public abstract class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Course courseModel) {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if (insertColumnName.equals(Course.getColumnName(Course.Columns.ID))) {
            parameters.addValue(parameterName, courseModel.getId());
        } else if (insertColumnName.equals(Course.getColumnName(Course.Columns.COURSE_NAME))) {
            parameters.addValue(parameterName, courseModel.getCourseName());
        } else if (insertColumnName.equals(Course.getColumnName(Course.Columns.COURSE_DESCRIPTION))) {
            parameters.addValue(parameterName, courseModel.getCourseDescription());
        } else if (insertColumnName.equals(Course.getColumnName(Course.Columns.CREATED_AT))) {
            parameters.addValue(parameterName, courseModel.getCreatedAt());
        } else if (insertColumnName.equals(Course.getColumnName(Course.Columns.UPDATED_AT))) {
            parameters.addValue(parameterName, courseModel.getUpdatedAt());

        } else {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Course courseModel) {
        if (keyHolderColumnName.equals(Course.getColumnName(Course.Columns.ID))) {
            courseModel.setId((Integer) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Course.getColumnName(Course.Columns.COURSE_NAME))) {
            courseModel.setCourseName((String) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Course.getColumnName(Course.Columns.COURSE_DESCRIPTION))) {
            courseModel.setCourseDescription((String) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Course.getColumnName(Course.Columns.CREATED_AT))) {
            courseModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        } else if (keyHolderColumnName.equals(Course.getColumnName(Course.Columns.UPDATED_AT))) {
            courseModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));

        } else
            {
                // should never end up here
                // lists should have already been validated
                throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
            }
        }

    }
