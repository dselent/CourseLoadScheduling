package org.dselent.scheduling.server.model.custommodel;

import org.dselent.scheduling.server.model.Model;

import java.sql.JDBCType;
import java.util.*;

public class GetAllRequests extends Model {

    // Column Names
    public static enum Columns{
        USERS_ID,
        COURSE_SECTIONS_ID
    }

    // Make a List of the Columns
    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    // Type Mapping
    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {

        for(Columns key : Columns.values())
        {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Columns.USERS_ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Columns.COURSE_SECTIONS_ID, JDBCType.INTEGER);
    };

    // Attributes
    private Integer usersId;
    private Integer courseSectionsId;

    // Column Methods

    public static JDBCType getColumnType(Columns column)
    {
        return COLUMN_TYPE_MAP.get(column);
    }

    public static String getColumnName(Columns column)
    {
        return column.toString().toLowerCase();
    }

    public static List<String> getColumnNameList()
    {
        List<String> columnNameList = new ArrayList<>();

        for(Columns column : COLUMN_LIST)
        {
            columnNameList.add(getColumnName(column));
        }

        return columnNameList;
    }

    // Setters and Getters

    public Integer getUsersId() { return usersId; }

    public void setUsersId(Integer usersId) { this.usersId = usersId; }

    public Integer getCourseSectionsId() { return courseSectionsId; }

    public void setCourseSectionsId(Integer courseSectionsId) { this.courseSectionsId = courseSectionsId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllRequests that = (GetAllRequests) o;
        return Objects.equals(usersId, that.usersId) &&
                Objects.equals(courseSectionsId, that.courseSectionsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, courseSectionsId);
    }

    @Override
    public String toString() {
        return "GetAllRequests{" +
                "usersId=" + usersId +
                ", courseSectionsId=" + courseSectionsId +
                '}';
    }
}
