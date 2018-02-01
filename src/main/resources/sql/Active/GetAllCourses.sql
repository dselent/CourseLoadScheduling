--Get all courses for professors:
SELECT  users.id, course_sections_id FROM faculty_courses 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
LEFT OUTER JOIN users ON (users_id = users.id) ORDER BY users.id ASC;
