--Get all courses for professors:
SELECT  users_id, course_sections_id FROM faculty_courses 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
ORDER BY users_id ASC;
