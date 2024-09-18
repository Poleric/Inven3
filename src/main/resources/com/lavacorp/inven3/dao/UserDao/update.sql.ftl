UPDATE Users
SET
    name = :name,
    hashed_password = :hashedPassword,
    role = :role
WHERE
    id = :id;