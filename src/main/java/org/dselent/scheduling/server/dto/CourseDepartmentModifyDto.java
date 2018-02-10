package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
/**
 * Created by Nathan on 2/9/2018.
 */
/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 *
 * @author dselent
 *
 */
public class CourseDepartmentModifyDto {
    private final Integer courseDepartmentId;
    private final Integer courseId;
    private final Integer departmentId;
    private final Integer courseNumber;

    @Generated("SparkTools")
    private CourseDepartmentModifyDto(Builder builder)
    {
        // can add defaults if null for other places where the builder pattern is used
        this.courseDepartmentId = builder.courseDepartmentId;
        this.courseId = builder.courseId;
        this.departmentId = builder.departmentId;
        this.courseNumber = builder.courseNumber;

        // making claim that none of these can be null
        // add other state checks here as necessary
        if(this.courseDepartmentId == null){
            throw new IllegalStateException("courseDepartmentId cannot be null");
        }
        else if(this.courseId == null)
        {
            throw new IllegalStateException("courseId cannot be null");
        }
        else if(this.departmentId == null)
        {
            throw new IllegalStateException("departmentId cannot be null");
        }
        else if(this.courseNumber == null)
        {
            throw new IllegalStateException("courseNumber cannot be null");
        }
    }

    public Integer getCourseDepartmentId(){
        return courseDepartmentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    @Override
    public int hashCode() {
        int result = courseDepartmentId != null ? courseDepartmentId.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (courseNumber != null ? courseNumber.hashCode() : 0);
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
        if (!(obj instanceof CourseDepartmentAddDto))
        {
            return false;
        }
        CourseDepartmentModifyDto other = (CourseDepartmentModifyDto) obj;
        if (courseId == null)
        {
            if (other.courseId != null)
            {
                return false;
            }
        }
        else if (!courseId.equals(other.courseId))
        {
            return false;
        }
        if (departmentId == null)
        {
            if (other.departmentId != null)
            {
                return false;
            }
        }
        else if (!departmentId.equals(other.departmentId))
        {
            return false;
        }
        if (courseNumber == null)
        {
            if (other.courseNumber != null)
            {
                return false;
            }
        }
        else if (!courseNumber.equals(other.courseNumber))
        {
            return false;
        }
        return true;
    }



    /**
     * Creates builder to build {@link UserRegisterDto}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to build {@link UserRegisterDto}.
     */
    @Generated("SparkTools")
    public static final class Builder
    {
        private Integer courseDepartmentId;
        private Integer courseId;
        private Integer departmentId;
        private Integer courseNumber;

        private Builder()
        {
        }

        public Builder withCourseDepartmentId(Integer courseDepartmentId){
            this.courseDepartmentId = courseDepartmentId;
            return this;
        }

        public Builder withCourseId(Integer courseId)
        {
            this.courseId = courseId;
            return this;
        }

        public Builder withDepartmentId(Integer departmentId)
        {
            this.departmentId = departmentId;
            return this;
        }

        public Builder withCourseNumber(Integer courseNumber)
        {
            this.courseNumber = courseNumber;
            return this;
        }
        public CourseDepartmentModifyDto build()
        {
            return new CourseDepartmentModifyDto(this);
        }
    }
}
