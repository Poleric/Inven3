package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.models.User;
import com.lavacorp.models.UserType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

public class UserDaoTests extends DaoNamedTest<User, UserDao> {
    public static final User[] DATA = {
            User.builder().id(1).name("Hong jun").password("minami").userType(UserType.STAFF).build(),
            User.builder().id(2).name("Mingy").password("SHIKANOKONOKOKOSHITANTAN").userType(UserType.ADMIN).build(),
    };

    public UserDaoTests() {
        super(UserDao.class, DATA, null);
    }

    public Stream<UserType> getUserTypes() {
        return Stream.of(UserType.values());
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getUserTypes")
    public void testSelectByUserType(UserType userType) {
        List<User> expected = getData().filter((user) -> user.getUserType() == userType).toList();
        List<User> actual = dao.selectByUserType(userType);

        assertEquals(expected, actual);
    }
}
