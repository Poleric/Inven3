package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.entities.User;

public class UserDaoTests extends DaoNamedTest<User, UserDao> {
    static final User[] DATA = {
            User.builder().id(1).name("Hong jun").password("minami").build(),
            User.builder().id(2).name("Mingy").password("SHIKANOKONOKOKOSHITANTAN").build(),
    };

    public UserDaoTests() {
        super(UserDao.class, DATA, null);
    }
}
