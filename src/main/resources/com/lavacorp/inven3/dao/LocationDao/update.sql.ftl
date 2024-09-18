UPDATE Location
SET
    name = :name,
    description = :description
WHERE
    id = :id;