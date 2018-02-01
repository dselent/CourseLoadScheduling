--Get course sections’ course information
SELECT course_sections.id, course_name, course_description FROM course_sections
LEFT OUTER JOIN courses ON (courses_id = courses.id)
ORDER BY course_sections.id ASC;
