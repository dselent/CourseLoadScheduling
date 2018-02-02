--Get all requests:
SELECT users_id, course_sections_id FROM course_requests 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
LEFT OUTER JOIN course_sections ON (course_sections_id = course_sections.id)
ORDER BY users_id ASC;