--Get all departments for a specific professor:
SELECT departments_id FROM faculty_departments
WHERE faculty_id = (SELECT faculty.id FROM faculty WHERE users_id = :userId)
ORDER BY departments_id ASC;

