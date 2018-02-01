--Get courses’ professor(s)
SELECT course_sections.id, faculty_id FROM course_sections
LEFT OUTER JOIN faculty_courses ON (course_sections.id = course_sections_id)
ORDER BY course_sections.id ASC;

