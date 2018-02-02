--Get a specific course(’s) professor(s)
SELECT users_id FROM faculty
WHERE id = (SELECT faculty_id FROM faculty_courses WHERE course_sections_id = :courseSectionsId)
ORDER BY users_id; 

