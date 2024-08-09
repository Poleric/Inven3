UPDATE User
SET
    username = :username,
    password = :password
WHERE
    id = :id