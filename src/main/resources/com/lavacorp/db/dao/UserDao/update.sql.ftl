UPDATE User
SET
    name = :name,
    hashed_password = :hashedPassword,
    user_type = :userType
WHERE
    id = :id