package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.entities.User;

public class UserDaoTests extends DaoNamedTest<User, UserDao> {
    public static final User[] DATA = {
            User.builder().id(1).name("Hong jun").hashedPassword("minami").build(),
            User.builder().id(2).name("Mingy").hashedPassword("SHIKANOKONOKOKOSHITANTAN").build(),
    };

    public UserDaoTests() {
        super(UserDao.class, DATA, null);
    }
}
