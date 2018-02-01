--Get required credits for all professors:
SELECT users.id, required_credits FROM users 
LEFT OUTER JOIN faculty ON (users.id = faculty.users_id)
ORDER BY users.id ASC;
