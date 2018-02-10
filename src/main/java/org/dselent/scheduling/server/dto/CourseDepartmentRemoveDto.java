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
public class CourseDepartmentRemoveDto {
    private final Integer courseDepartmentId;

    @Generated("SparkTools")
    private CourseDepartmentRemoveDto(Builder builder)
    {
        // can add defaults if null for other places where the builder pattern is used
        this.courseDepartmentId = builder.courseDepartmentId;

        // making claim that none of these can be null
        // add other state checks here as necessary
        if(this.courseDepartmentId == null){
            throw new IllegalStateException("courseDepartmentId cannot be null");
        }
    }

    public Integer getCourseDepartmentId(){
        return courseDepartmentId;
    }

    @Override
    public int hashCode() {
        return courseDepartmentId != null ? courseDepartmentId.hashCode() : 0;
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
        CourseDepartmentRemoveDto other = (CourseDepartmentRemoveDto) obj;
        if (courseDepartmentId == null)
        {
            if (other.courseDepartmentId != null)
            {
                return false;
            }
        }
        else if (!courseDepartmentId.equals(other.courseDepartmentId))
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

        private Builder()
        {
        }

        public Builder withCourseDepartmentId(Integer courseDepartmentId){
            this.courseDepartmentId = courseDepartmentId;
            return this;
        }

        public CourseDepartmentRemoveDto build()
        {
            return new CourseDepartmentRemoveDto(this);
        }
    }
}

