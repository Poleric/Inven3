UPDATE Supplier
SET
    name = :name,
    address = :address,
    phone_number = :phoneNumber,
    email = :email
WHERE
    id = :id;