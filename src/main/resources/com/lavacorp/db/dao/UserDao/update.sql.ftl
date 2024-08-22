UPDATE User
SET
    name = :name,
    hashed_password = :hashedPassword
WHERE
    id = :id