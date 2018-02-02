package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/1/2018.
 */
public class FacultyExtractor extends Extractor<List<Faculty>>
{
    @Override
    public List<Faculty> extractData(ResultSet rs) throws SQLException
    {
        List<Faculty> resultList = new ArrayList<>();

        while(rs.next())
        {
            Faculty result = new Faculty();

            result.setId(rs.getInt(Faculty.getColumnName(Faculty.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setUsersId(rs.getInt(Faculty.getColumnName(Faculty.Columns.USERS_ID)));

            if(rs.wasNull())
            {
                result.setUsersId(null);
            }

            result.setRequiredCredits(rs.getInt(Faculty.getColumnName(Faculty.Columns.REQUIRED_CREDITS)));

            result.setCreatedAt(rs.getTimestamp(Faculty.getColumnName(Faculty.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(Faculty.getColumnName(Faculty.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
