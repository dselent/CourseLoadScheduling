package org.dselent.scheduling.server.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Register all custom SQL query files here
 * 
 * @author dselent
 *
 */
public class QueryPathConstants
{
	private static String BASE_QUERY_PATH = "sql" + File.separator + "Active" + File.separator;
	private static String SQL_EXTENSION = ".sql";

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "CustomUsersWithRole" + SQL_EXTENSION;
	private static String GET_ALL_COURSES_PATH = BASE_QUERY_PATH + "GetAllCourses" + SQL_EXTENSION;
	private static String GET_ALL_PROFS_DEPTS_PATH = BASE_QUERY_PATH + "GetAllProfsDepts" + SQL_EXTENSION;
	private static String GET_ALL_REQUESTS_PATH = BASE_QUERY_PATH + "GetAllRequests" + SQL_EXTENSION;
	private static String GET_COURSE_INFO_PATH = BASE_QUERY_PATH + "GetCourseInfo" + SQL_EXTENSION;
	private static String GET_COURSES_ONE_PROF_PATH = BASE_QUERY_PATH + "GetCoursesOneProf" + SQL_EXTENSION;
	private static String GET_DEPT_ONE_COURSE_PATH = BASE_QUERY_PATH + "GetDeptOneCourse" + SQL_EXTENSION;
	private static String GET_PROFS_ONE_COURSE_PATH = BASE_QUERY_PATH + "GetProfsOneCourse" + SQL_EXTENSION;
	private static String GET_REQUIRED_CREDITS_PATH = BASE_QUERY_PATH + "GetRequiredCredits" + SQL_EXTENSION;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);

	
	private QueryPathConstants()
	{
		
	}
	
	public static String readFile(String path)
	{
		byte[] encodedbytes = null;
		
		try
		{
			Resource resource = new ClassPathResource(path);
			encodedbytes = Files.readAllBytes(Paths.get(resource.getURI()));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return new String(encodedbytes);
	}

}
