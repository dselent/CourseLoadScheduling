package org.dselent.scheduling.server.extractor.customextractor;

import org.dselent.scheduling.server.extractor.Extractor;
import org.dselent.scheduling.server.model.custommodel.GetAllRequests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllRequestsExtractor extends Extractor<List<GetAllRequests>> {

    @Override
    public List<GetAllRequests> extractData(ResultSet rs) throws SQLException {
        List<GetAllRequests> resultList = new ArrayList<>();

        while(rs.next()){
            GetAllRequests result = new GetAllRequests();

            result.setUsersId(rs.getInt(GetAllRequests.getColumnName(GetAllRequests.Columns.USERS_ID)));
            result.setCourseSectionsId(rs.getInt(GetAllRequests.getColumnName(GetAllRequests.Columns.COURSE_SECTIONS_ID)));

            resultList.add(result);
        }

        return resultList;
    }
}
