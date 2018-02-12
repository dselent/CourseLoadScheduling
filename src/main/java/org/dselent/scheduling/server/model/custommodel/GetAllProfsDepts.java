package org.dselent.scheduling.server.model.custommodel;

import org.dselent.scheduling.server.model.Model;

import java.sql.JDBCType;
import java.util.*;

/**
 * Created by Nathan on 2/2/2018.
 */

public class GetAllProfsDepts extends Model {
    // Column Names
    public static enum Columns
    {
        USERS_ID,
        DEPARTMENTS_ID
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
        COLUMN_TYPE_MAP.put(Columns.DEPARTMENTS_ID, JDBCType.INTEGER);
    };

    // Attributes
    private Integer usersId;
    private Integer departmentsId;

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

    // Attribute Setters and Getters

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Integer departmentsId) {
        this.departmentsId = departmentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetAllProfsDepts that = (GetAllProfsDepts) o;

        if (usersId != null ? !usersId.equals(that.usersId) : that.usersId != null) return false;
        return departmentsId != null ? departmentsId.equals(that.departmentsId) : that.departmentsId == null;
    }

    @Override
    public int hashCode() {
        int result = usersId != null ? usersId.hashCode() : 0;
        result = 31 * result + (departmentsId != null ? departmentsId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetAllProfsDepts{" +
                "usersId=" + usersId +
                ", departmentsId=" + departmentsId +
                '}';
    }
}
