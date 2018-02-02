-- get all user information for users who have a specified roleId

SELECT users.*
FROM users
WHERE id IN
(
    SELECT user_id
    FROM users_permissions
    WHERE role = :role
)
ORDER BY users.id ASC;

