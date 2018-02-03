--Get professors departments:
SELECT users_id, departments_id FROM faculty_departments 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
ORDER BY users_id ASC;

