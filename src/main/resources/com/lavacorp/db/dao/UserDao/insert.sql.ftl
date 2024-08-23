INSERT INTO User (name, hashed_password)
VALUES (:name, :hashedPassword)
RETURNING *