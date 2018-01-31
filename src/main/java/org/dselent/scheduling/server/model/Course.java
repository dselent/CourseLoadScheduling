package org.dselent.scheduling.server.model;


import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Course extends Model{

    // Table Name
    public static final String TABLE_NAME = "courses";

    // Column Names
    public static enum Columns
    {
        ID,
        COURSE_NAME,
        COURSE_DESCRIPTION,
        CREATED_AT,
        UPDATED_AT
    }

    // Make a List of the Columns
    private static final List<Columns> COLUMN_LIST = new ArrayList<>();

    // Type Mapping
    private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();

    static
    {
        for(Course.Columns key : Course.Columns.values())
        {
            COLUMN_LIST.add(key);
        }

        COLUMN_TYPE_MAP.put(Course.Columns.ID, JDBCType.INTEGER);
        COLUMN_TYPE_MAP.put(Course.Columns.COURSE_NAME, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Course.Columns.COURSE_DESCRIPTION, JDBCType.VARCHAR);
        COLUMN_TYPE_MAP.put(Course.Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
        COLUMN_TYPE_MAP.put(Course.Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
    };

    // Attributes
    private Integer id;
    private String courseName;
    private String courseDescription;
    private Instant createdAt;
    private Instant updatedAt;

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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseDescription()
    {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription)
    {
        this.courseDescription = courseDescription;
    }

    public Instant getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt)
    {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(Timestamp createdAt)
    {
        if(createdAt != null)
        {
            this.createdAt = createdAt.toInstant();
        }
    }

    public Instant getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt)
    {
        if(updatedAt != null)
        {
            this.updatedAt = updatedAt.toInstant();
        }
    }

    // Remaining Methods

    @Override
    public int hashCode() // Overrides hashCode to be specific to this Model
    {
        // Essentially creates a custom hashcode for the entire object based on each element

        final int prime = 31; //Should this be unique to each object?
        int result = 1;

        // Not sure if the order of these should be obfuscated
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((courseDescription == null) ? 0 : courseDescription.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) // If this object is identically itself
        {
            return true;
        }

        if (obj == null) // If the comparison object doesn't exist
        {
            return false;
        }

        if (!(obj instanceof Course)) // If the comparison object isn't a Course
        {
            return false;
        }

        Course other = (Course) obj; // Type cast the comparison object to Course for some reason?

        // The comparison order here is dependent on what is most likely to cause a FALSE
        if (id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        }
        else if (!id.equals(other.id))
        {
            return false;
        }

        if (createdAt == null)
        {
            if (other.createdAt != null)
            {
                return false;
            }
        }
        else if (!createdAt.equals(other.createdAt))
        {
            return false;
        }

        if (updatedAt == null)
        {
            if (other.updatedAt != null)
            {
                return false;
            }
        }
        else if (!updatedAt.equals(other.updatedAt))
        {
            return false;
        }

        if (courseName == null)
        {
            if (other.courseName != null)
            {
                return false;
            }
        }
        else if (!courseName.equals(other.courseName))
        {
            return false;
        }

        if (courseDescription == null)
        {
            if (other.courseDescription != null)
            {
                return false;
            }
        }
        else if (!courseDescription.equals(other.courseDescription))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Users [id=");
        builder.append(id);
        builder.append(", courseName=");
        builder.append(courseName);
        builder.append(", courseDescription=");
        builder.append(courseDescription);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append("]");
        return builder.toString();
    }

}
