
/**
 * Created by Nathan on 2/8/2018.
 */

package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 *
 * @author dselent
 *
 */
public class CourseAddDto {
    private final String courseName;
    private final String courseDept;
    private final String courseDescription;
    // I added to the auto-generated code
    @Generated("SparkTools")
    private UserRegisterDto(Builder builder)
    {
        // can add defaults if null for other places where the builder pattern is used

        this.courseName = builder.courseName;
        this.courseDept = builder.courseDept;
        this.courseDescription = builder.courseDescription;

        // making claim that none of these can be null
        // add other state checks here as necessary

        if(this.courseName == null)
        {
            throw new IllegalStateException("courseName cannot be null");
        }
        else if(this.courseDept == null)
        {
            throw new IllegalStateException("courseDept cannot be null");
        }
        else if(this.courseDescription == null)
        {
            throw new IllegalStateException("courseDescription cannot be null");
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public int hashCode() {
        int result = courseName != null ? courseName.hashCode() : 0;
        result = 31 * result + (courseDept != null ? courseDept.hashCode() : 0);
        result = 31 * result + (courseDescription != null ? courseDescription.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof CourseAddDto))
        {
            return false;
        }
        CourseAddDto other = (CourseAddDto) obj;
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
        if (courseDept == null)
        {
            if (other.courseDept != null)
            {
                return false;
            }
        }
        else if (!courseDept.equals(other.courseDept))
        {
            return false;
        }
        if (courseDescription == null)
        {
            if (other.lastName != null)
            {
                return false;
            }
        }
        else if (!lastName.equals(other.lastName))
        {
            return false;
        }
        if (password == null)
        {
            if (other.password != null)
            {
                return false;
            }
        }
        else if (!password.equals(other.password))
        {
            return false;
        }
        if (userName == null)
        {
            if (other.userName != null)
            {
                return false;
            }
        }
        else if (!userName.equals(other.userName))
        {
            return false;
        }
        return true;
    }
}
