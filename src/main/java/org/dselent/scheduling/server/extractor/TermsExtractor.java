package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Term;

public class TermsExtractor extends Extractor<List<Term>>{

    @Override
    public List<Term> extractData(ResultSet rs) throws SQLException{
        List<Term> resultList = new ArrayList<>();

        while(rs.next()){
            Term result = new Term();

            result.setId(rs.getInt(Term.getColumnName(Term.Columns.ID)));

            if(rs.wasNull()){
                result.setId(null);
            }

            result.setTermName(rs.getString(Term.getColumnName(Term.Columns.TERM_NAME)));

            result.setCreatedAt(rs.getTimestamp(Term.getColumnName(Term.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(Term.getColumnName(Term.Columns.UPDATED_AT)));

            resultList.add(result);
        }

        return resultList;
    }


}
