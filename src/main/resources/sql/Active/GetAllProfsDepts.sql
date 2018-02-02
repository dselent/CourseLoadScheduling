--Get professors departments:
SELECT users.id, departments_id FROM faculty_departments 
LEFT OUTER JOIN faculty ON (faculty.id = faculty_id)
LEFT OUTER JOIN users ON (users.id = users_id)
ORDER BY users.id ASC;

