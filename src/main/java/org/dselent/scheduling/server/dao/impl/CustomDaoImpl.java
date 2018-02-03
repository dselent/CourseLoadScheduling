package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.CoursesExtractor;
import org.dselent.scheduling.server.extractor.DepartmentsExtractor;
import org.dselent.scheduling.server.extractor.FacultyCoursesExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.extractor.customextractor.GetAllCoursesExtractor;
import org.dselent.scheduling.server.extractor.customextractor.GetAllProfsDeptsExtractor;
import org.dselent.scheduling.server.extractor.customextractor.GetAllRequestsExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.*;
import org.dselent.scheduling.server.model.custommodel.GetAllCourses;
import org.dselent.scheduling.server.model.custommodel.GetAllProfsDepts;
import org.dselent.scheduling.server.model.custommodel.GetAllRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<User> customUsersWithRole(int roleId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("roleId", roleId);
	    return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	@Override
	public List<GetAllCourses> getAllCourses()
	{
		GetAllCoursesExtractor extractor = new GetAllCoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_ALL_COURSES_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);

	}
	@Override
	public List<GetAllProfsDepts> getAllProfsDepts()
	{
		GetAllProfsDeptsExtractor extractor = new GetAllProfsDeptsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_ALL_PROFS_DEPTS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	@Override
	public List<GetAllRequests> getAllRequests()
	{
		GetAllRequestsExtractor extractor = new GetAllRequestsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_ALL_REQUESTS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	@Override
	public List<FacultyCourse> getCourseOneProf(int userId)
	{
		FacultyCoursesExtractor extractor = new FacultyCoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_COURSES_ONE_PROF_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	@Override
	public List<Department> getDeptOneProf(int userId)
	{
		DepartmentsExtractor extractor = new DepartmentsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_DEPT_ONE_PROF_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	@Override
	public List<User>  getProfsOneCourse(int courseSectionsId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_PROFS_ONE_COURSE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseSectionsId", courseSectionsId);
		return namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	}
	
}
