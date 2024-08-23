INSERT INTO Category (name, description)
VALUES (:name, :description)
RETURNING *