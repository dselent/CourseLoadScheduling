package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseDepartment;

public class CourseDepartmentsExtractor extends Extractor<List<CourseDepartment>>
{
    @Override
    public List<CourseDepartment> extractData(ResultSet rs) throws SQLException
    {
        List<CourseDepartment> resultList = new ArrayList<>();

        while(rs.next())
        {
            CourseDepartment result = new CourseDepartment();

            result.setId(rs.getInt(CourseDepartment.getColumnName(CourseDepartment.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setCourseId(rs.getInt(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSES_ID)));
            result.setDepartmentId(rs.getInt(CourseDepartment.getColumnName(CourseDepartment.Columns.DEPARTMENTS_ID)));
            result.setCourseNumber(rs.getInt(CourseDepartment.getColumnName(CourseDepartment.Columns.COURSE_NUMBER)));
            result.setCreatedAt(rs.getTimestamp(CourseDepartment.getColumnName(CourseDepartment.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseDepartment.getColumnName(CourseDepartment.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }

}
