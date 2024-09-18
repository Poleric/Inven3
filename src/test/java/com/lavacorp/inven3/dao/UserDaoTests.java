package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDaoTests;
import com.lavacorp.inven3.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTests extends NamedDaoTests<User, UserDao> {

    @Autowired
    public UserDaoTests(UserDao dao) {
        super(dao);
    }

}
