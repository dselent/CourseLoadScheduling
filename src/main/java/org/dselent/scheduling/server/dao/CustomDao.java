package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.*;

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
	public List<GetAllCourses> getAllCourses();
	public List<GetAllProfsDepts> getAllProfsDepts();
	public List<GetAllRequests> getAllRequests();
	public List<FacultyCourse> getCourseOneProf(int facultyId);
	public List<Department> getDeptOneProf(int facultyId);
	public List<User>  getProfsOneCourse(int courseSectionId);
}
