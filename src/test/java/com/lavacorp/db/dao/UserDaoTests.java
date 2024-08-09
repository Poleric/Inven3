package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoTest;
import com.lavacorp.entities.User;

public class UserDaoTests extends DaoTest<User, UserDao> {
    static final User[] DATA = {
            User.builder().id(1).username("Hong jun").password("minami").build(),
            User.builder().id(2).username("Mingy").password("SHIKANOKONOKOKOSHITANTAN").build(),
    };

    public UserDaoTests() {
        super(UserDao.class, DATA, null);
    }
}
