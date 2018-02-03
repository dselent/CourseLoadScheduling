package org.dselent.scheduling.server.extractor.customextractor;

import org.dselent.scheduling.server.extractor.Extractor;
import org.dselent.scheduling.server.model.custommodel.GetAllProfsDepts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/2/2018.
 */
public class GetAllProfsDeptsExtractor extends Extractor<List<GetAllProfsDepts>> {
    @Override
    public List<GetAllProfsDepts> extractData(ResultSet rs) throws SQLException
    {
        List<GetAllProfsDepts> resultList = new ArrayList<>();

        while(rs.next())
        {
            GetAllProfsDepts result = new GetAllProfsDepts();

            result.setUsersId(rs.getInt(GetAllProfsDepts.getColumnName(GetAllProfsDepts.Columns.USERS_ID)));

            if(rs.wasNull())
            {
                result.setUsersId(null);
            }

            result.setDepartmentsId(rs.getInt(GetAllProfsDepts.getColumnName(GetAllProfsDepts.Columns.DEPARTMENTS_ID)));

            if(rs.wasNull())
            {
                result.setUsersId(null);
            }

            resultList.add(result);
        }

        return resultList;
    }
}
