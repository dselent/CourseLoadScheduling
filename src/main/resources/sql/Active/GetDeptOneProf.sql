--Get all departments for a specific professor:
SELECT departments.* FROM departments WHERE departments.id = (SELECT departments_id FROM faculty_departments
WHERE faculty_id = (SELECT faculty.id FROM faculty WHERE users_id = :userId))
ORDER BY departments.id ASC;

