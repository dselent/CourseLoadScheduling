package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Location;

public class Locations extends Extractor<List<Location>> {

    @Override
    public List<Location> extractData(ResultSet rs) throws SQLException{
        List<Location> resultList = new ArrayList<>();

        while(rs.next()){
            Location result = new Location();

            result.setId(rs.getInt(Location.getColumnName(Location.Columns.ID)));

            if(rs.wasNull()){
                result.setId(null);
            }

            result.setBuilding(rs.getString(Location.getColumnName(Location.Columns.BUILDING)));
            result.setRoom(rs.getInt(Location.getColumnName(Location.Columns.ROOM)));
            result.setRoomSize(rs.getInt(Location.getColumnName(Location.Columns.ROOM_SIZE)));

            result.setCreatedAt(rs.getTimestamp(Location.getColumnName(Location.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(Location.getColumnName(Location.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }
}
