package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Department;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.CourseRequest;

import org.dselent.scheduling.server.model.custommodel.GetAllCourses;
import org.dselent.scheduling.server.model.custommodel.GetAllProfsDepts;
import org.dselent.scheduling.server.model.custommodel.GetAllRequests;
import org.springframework.stereotype.Repository;

/**
 * Interface for all daos for custom queries.
 * 
 * @author dselent
 *
 */
@Repository
public interface CustomDao
{
	// custom queries here
	public List<User> customUsersWithRole(int roleId);
	public List<GetAllCourses> getAllCourses(int facultyId);
	public List<GetAllProfsDepts> getAllProfsDepts();
	public List<GetAllRequests> getAllRequests();
	public List<Course> getCourseOneProf(int facultyId);
	public List<Department> getDeptOneProf(int facultyId);
	public List<User>  getProfsOneCourse(int courseSectionId);
}
