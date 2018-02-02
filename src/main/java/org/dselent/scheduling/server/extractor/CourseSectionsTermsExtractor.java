package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseSectionTerm;

public class CourseSectionsTermsExtractor extends Extractor<List<CourseSectionTerm>>
{
    @Override
    public List<CourseSectionTerm> extractData(ResultSet rs) throws SQLException
    {
        List<CourseSectionTerm> resultList = new ArrayList<>();

        while(rs.next())
        {
            CourseSectionTerm result = new CourseSectionTerm();

            result.setId(rs.getInt(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setCourseSectionsId(rs.getInt(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.COURSE_SECTIONS_ID)));
            result.setTermsId(rs.getInt(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.TERMS_ID)));
            
            result.setCreatedAt(rs.getTimestamp(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(CourseSectionTerm.getColumnName(CourseSectionTerm.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }

}
