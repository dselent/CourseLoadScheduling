package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.FacultyDepartment;

public class FacultyDepartmentsExtractor extends Extractor<List<FacultyDepartment>>
{
    @Override
    public List<FacultyDepartment> extractData(ResultSet rs) throws SQLException
    {
        List<FacultyDepartment> resultList = new ArrayList<>();

        while(rs.next())
        {
            FacultyDepartment result = new FacultyDepartment();

            result.setId(rs.getInt(FacultyDepartment.getColumnName(FacultyDepartment.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setFacultyID(rs.getInt(FacultyDepartment.getColumnName(FacultyDepartment.Columns.FACULTY_ID)));
            result.setDepartmentsID(rs.getInt(FacultyDepartment.getColumnName(FacultyDepartment.Columns.DEPARTMENTS_ID)));


            result.setCreatedAt(rs.getTimestamp(FacultyDepartment.getColumnName(FacultyDepartment.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(FacultyDepartment.getColumnName(FacultyDepartment.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }

}
