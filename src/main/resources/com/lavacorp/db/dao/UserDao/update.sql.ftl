UPDATE User
SET
    name = :name,
    password = :password
WHERE
    id = :id