package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseRequest;

public class CourseRequestsExtractor extends Extractor<List<CourseRequest>>
{
    @Override
    public List<CourseRequest> extractData(ResultSet rs) throws SQLException
    {
        List<CourseRequest> resultList = new ArrayList<>();

        while(rs.next())
        {
            CourseRequest result = new CourseRequest();

            result.setId(rs.getInt(CourseRequest.getColumnName(CourseRequest.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setFacultyId(rs.getInt(CourseRequest.getColumnName(CourseRequest.Columns.FACULTY_ID)));
            result.setCourseSectionsId(rs.getInt(CourseRequest.getColumnName(CourseRequest.Columns.COURSE_SECTIONS_ID)));

            result.setCreatedAt(rs.getTimestamp(CourseRequest.getColumnName(CourseRequest.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseRequest.getColumnName(CourseRequest.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }

}

