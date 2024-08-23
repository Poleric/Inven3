package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.models.User;

public class UserDaoTests extends DaoNamedTest<User, UserDao> {
    public static final User[] DATA = {
            User.builder().id(1).name("Hong jun").password("minami").build(),
            User.builder().id(2).name("Mingy").password("SHIKANOKONOKOKOSHITANTAN").build(),
    };

    public UserDaoTests() {
        super(UserDao.class, DATA, null);
    }
}
