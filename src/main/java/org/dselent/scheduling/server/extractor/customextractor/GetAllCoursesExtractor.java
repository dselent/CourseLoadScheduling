package org.dselent.scheduling.server.extractor.customextractor;

import org.dselent.scheduling.server.extractor.Extractor;
import org.dselent.scheduling.server.model.custommodel.GetAllCourses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllCoursesExtractor extends Extractor<List<GetAllCourses>> {
    @Override
    public List<GetAllCourses> extractData(ResultSet rs) throws SQLException {
        List<GetAllCourses> resultList = new ArrayList<>();

        while(rs.next()){
            GetAllCourses result = new GetAllCourses();

            result.setUsersId(rs.getInt(GetAllCourses.getColumnName(GetAllCourses.Columns.USERS_ID)));
            result.setCourseSectionsId(rs.getInt(GetAllCourses.getColumnName(GetAllCourses.Columns.COURSE_SECTIONS_ID)));

            resultList.add(result);
        }

        return resultList;
    }
}
