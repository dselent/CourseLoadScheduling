--Get all requests:
SELECT course_sections_id, users_id FROM course_requests 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
LEFT OUTER JOIN course_sections ON (course_sections_id = course_sections.id)
ORDER BY course_sections_id ASC;