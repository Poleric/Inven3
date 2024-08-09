package com.lavacorp.entities.generic;

import com.lavacorp.db.Database;
import org.jdbi.v3.core.Handle;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.entities.User;
import lombok.Data;

@Data
public class login {
    public void registerUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        try (Handle handle = Database.getJdbi().open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.create(user);
        }
        System.out.println("User registered: " + name);

    }
}
