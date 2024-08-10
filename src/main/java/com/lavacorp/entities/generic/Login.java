package com.lavacorp.entities.generic;

import com.lavacorp.db.Database;
import org.jdbi.v3.core.Handle;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.entities.User;
import lombok.Data;

import java.util.Scanner;

@Data
public class Login {
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

    public void loginUser(String name, String password) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Your Name: ");

    }

}
