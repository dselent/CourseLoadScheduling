package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.file.Locations;
import org.dselent.scheduling.server.dao.LocationsDao;
import org.dselent.scheduling.server.dto.LocationAddDto;
import org.dselent.scheduling.server.dto.LocationModifyDto;
import org.dselent.scheduling.server.dto.LocationRemoveDto;
import org.dselent.scheduling.server.model.Location;
import org.dselent.scheduling.server.service.LocationService;

import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.dselent.scheduling.server.sqlutils.ComparisonOperator.EQUAL;
/**
 * Created by Nathan on 2/9/2018.
 */
@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationsDao locationsDao;

    public LocationServiceImpl(){

    }

    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.LocationService#addLocation(org.dselent.scheduling.server.dto.LocationAddDto)
     */
    @Transactional
    @Override
    public List<Integer> addLocation(LocationAddDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();

        Location Location = new Location();
        Location.setBuilding(dto.getBuilding());
        Location.setRoom(dto.getRoom());
        Location.setRoom(dto.getRoomSize());


        /*dunno what to do with this. we'll probably need to add a new thing in BaseDaoImpl*/
        String LocationDept = dto.getLocationDept();

        List<String> LocationInsertColumnNameList = new ArrayList<>();
        List<String> LocationKeyHolderColumnNameList = new ArrayList<>();

        LocationInsertColumnNameList.add(Location.getColumnName(Location.Columns.BUILDING));
        LocationInsertColumnNameList.add(Location.getColumnName(Location.Columns.ROOM));
        LocationInsertColumnNameList.add(Location.getColumnName(Location.Columns.ROOM_SIZE));
        LocationKeyHolderColumnNameList.add(Location.getColumnName(Location.Columns.ID));
        LocationKeyHolderColumnNameList.add(Location.getColumnName(Location.Columns.CREATED_AT));
        LocationKeyHolderColumnNameList.add(Location.getColumnName(Location.Columns.UPDATED_AT));

        rowsAffectedList.add(locationsDao.insert(Location, LocationInsertColumnNameList, LocationKeyHolderColumnNameList));
        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> modifyLocation(LocationModifyDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer LocationId = dto.getLocationId();
        String LocationBuilding = dto.getBuilding();
        Integer LocationDescription = dto.getLocationId();
        Integer LocationRoom = dto.getRoom();
        Integer LocationRoomSize = dto.getRoomSize();

        /*I have no idea how to modify a Location's department. the system'll work okay without it though*/



        queryTermList.add(new QueryTerm(Location.getColumnName(Location.Columns.ID),EQUAL,LocationId,null));

        rowsAffectedList.add(locationsDao.update(Location.getColumnName(Location.Columns.BUILDING),LocationBuilding,queryTermList));
        rowsAffectedList.add(locationsDao.update(Location.getColumnName(Location.Columns.ROOM),LocationRoom,queryTermList));
        rowsAffectedList.add(locationsDao.update(Location.getColumnName(Location.Columns.ROOM_SIZE),LocationRoomSize,queryTermList));
        rowsAffectedList.add(locationsDao.update(Location.getColumnName(Location.Columns.ID),LocationId,queryTermList));
        return rowsAffectedList;
    }

    @Transactional
    @Override
    public List<Integer> removeLocation(LocationRemoveDto dto) throws SQLException{
        List<Integer> rowsAffectedList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();

        Integer LocationId = dto.getLocationId();
        queryTermList.add(new QueryTerm(Location.getColumnName(Location.Columns.ID),EQUAL,LocationId,null));

        rowsAffectedList.add(locationsDao.delete(queryTermList));

        return rowsAffectedList;
    }
}
