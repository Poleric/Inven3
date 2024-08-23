package com.lavacorp.models.generic;

import com.lavacorp.db.Database;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Handle;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.User;
import org.jetbrains.annotations.Nullable;


@UtilityClass
@Log4j2
public class Login {
    public static void register(String name, String password) {
        User user = User.builder()
                .name(name)
                .password(password)
                .build();

        try (Handle handle = Database.getJdbi().open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.insert(user);
        }

        log.info("Registered user `{}`", user.getName());
    }

    public static @Nullable User login(String name, String password) {
        User user;

        try (Handle handle = Database.getJdbi().open()) {
            UserDao dao = handle.attach(UserDao.class);
            user = dao.selectByName(name);
        }

        if (user == null) {
            log.info("User `{}` not found", name);
            return null;
        }

        if (user.comparePassword(password)) {
            log.info("User `{}` logged in", user.getName());
            return user;
        }

        log.info("`{}` is not user `{}` password", password, user.getName());
        return null;
    }
}
