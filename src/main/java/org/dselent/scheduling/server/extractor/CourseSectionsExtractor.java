package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.CourseSection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/1/2018.
 */
public class CourseSectionsExtractor extends Extractor<List<CourseSection>>
{
    @Override
    public List<CourseSection> extractData(ResultSet rs) throws SQLException
    {
        List<CourseSection> resultList = new ArrayList<>();

        while(rs.next())
        {
            CourseSection result = new CourseSection();

            result.setId(rs.getInt(CourseSection.getColumnName(CourseSection.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setCoursesId(rs.getInt(CourseSection.getColumnName(CourseSection.Columns.COURSES_ID)));

            if(rs.wasNull())
            {
                result.setCoursesId(null);
            }

            result.setSectionType(rs.getString(CourseSection.getColumnName(CourseSection.Columns.SECTION_TYPE)));

            result.setCreatedAt(rs.getTimestamp(CourseSection.getColumnName(CourseSection.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseSection.getColumnName(CourseSection.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
