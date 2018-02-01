package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.CourseTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseTimeExtractor extends Extractor<List<CourseTime>> {
    @Override
    public List<CourseTime> extractData(ResultSet rs) throws SQLException {
        List<CourseTime> resultList = new ArrayList<>();

        while (rs.next()) {
            CourseTime result = new CourseTime();

            result.setId(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.ID)));

            if (rs.wasNull()) {
                result.setId(null);
            }


//            result.setFacultyId(rs.getInt(CourseRequest.getColumnName(CourseRequest.Columns.FACULTY_ID)));
            result.setCourseSectionsId(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.COURSE_SECTIONS_ID)));
            if (rs.wasNull()) {
                result.setCourseSectionsId(null);
            }

            result.setCreatedAt(rs.getTimestamp(CourseTime.getColumnName(CourseTime.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseTime.getColumnName(CourseTime.Columns.UPDATED_AT)));


            result.setDayOfWeek(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.DAY_OF_WEEK)));
            result.setStartTime(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.START_TIME)));
            result.setEndTime(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.END_TIME)));
            result.setLocationID(rs.getInt(CourseTime.getColumnName(CourseTime.Columns.LOCATION_ID)));
            if (rs.wasNull()) {
                result.setLocationID(null);
            }

            result.setCreatedAt(rs.getTimestamp(CourseTime.getColumnName(CourseTime.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseTime.getColumnName(CourseTime.Columns.UPDATED_AT)));


            resultList.add(result);
        }

        return resultList;
    }
}
