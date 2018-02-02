package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsExtractor extends Extractor<List<Department>> {
    @Override
    public List<Department> extractData(ResultSet rs) throws SQLException {
        List<Department> resultList = new ArrayList<>();

        while (rs.next()) {
            Department result = new Department();

            result.setId(rs.getInt(Department.getColumnName(Department.Columns.ID)));

            if (rs.wasNull()) {
                result.setId(null);
            }

            result.setDepartment(rs.getString(Department.getColumnName(Department.Columns.DEPARTMENT)));

            result.setCreatedAt(rs.getTimestamp(Department.getColumnName(Department.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(Department.getColumnName(Department.Columns.UPDATED_AT)));


            resultList.add(result);
        }

        return resultList;
    }
}
