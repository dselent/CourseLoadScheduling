package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.UserPermission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/1/2018.
 */
public class UsersPermissionsExtractor extends Extractor<List<UserPermission>>
{
    @Override
    public List<UserPermission> extractData(ResultSet rs) throws SQLException
    {
        List<UserPermission> resultList = new ArrayList<>();

        while(rs.next())
        {
            UserPermission result = new UserPermission();

            result.setId(rs.getInt(UserPermission.getColumnName(UserPermission.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setUsersId(rs.getInt(UserPermission.getColumnName(UserPermission.Columns.USERS_ID)));

            if(rs.wasNull())
            {
                result.setUsersId(null);
            }

            result.setRole(rs.getString(UserPermission.getColumnName(UserPermission.Columns.ROLE)));

            result.setCreatedAt(rs.getTimestamp(UserPermission.getColumnName(UserPermission.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(UserPermission.getColumnName(UserPermission.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
