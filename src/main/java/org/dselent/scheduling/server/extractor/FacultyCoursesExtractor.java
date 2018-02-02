package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.FacultyCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/1/2018.
 */
public class FacultyCoursesExtractor extends Extractor<List<FacultyCourse>>
{
    @Override
    public List<FacultyCourse> extractData(ResultSet rs) throws SQLException
    {
        List<FacultyCourse> resultList = new ArrayList<>();

        while(rs.next())
        {
            FacultyCourse result = new FacultyCourse();

            result.setId(rs.getInt(FacultyCourse.getColumnName(FacultyCourse.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setFacultyID(rs.getInt(FacultyCourse.getColumnName(FacultyCourse.Columns.FACULTY_ID)));

            if(rs.wasNull())
            {
                result.setFacultyID(null);
            }

            result.setCourseSectionID(rs.getInt(FacultyCourse.getColumnName(FacultyCourse.Columns.COURSE_SECTIONS_ID)));

            if(rs.wasNull())
            {
                result.setCourseSectionID(null);
            }

            result.setCreatedAt(rs.getTimestamp(FacultyCourse.getColumnName(FacultyCourse.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(FacultyCourse.getColumnName(FacultyCourse.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
