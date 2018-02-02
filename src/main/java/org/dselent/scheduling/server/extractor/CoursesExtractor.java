package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/1/2018.
 */
public class CoursesExtractor extends Extractor<List<Course>>{
    @Override
    public List<Course> extractData(ResultSet rs) throws SQLException
    {
        List<Course> resultList = new ArrayList<>();

        while(rs.next())
        {
            Course result = new Course();

            result.setId(rs.getInt(Course.getColumnName(Course.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setCourseName(rs.getString(Course.getColumnName(Course.Columns.COURSE_NAME)));
            result.setCourseDescription(rs.getString(Course.getColumnName(Course.Columns.COURSE_DESCRIPTION)));

            result.setCreatedAt(rs.getTimestamp(Course.getColumnName(Course.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(Course.getColumnName(Course.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
