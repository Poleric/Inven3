INSERT INTO User (name, hashed_password, user_type)
VALUES (:name, :hashedPassword, :userType)
RETURNING *