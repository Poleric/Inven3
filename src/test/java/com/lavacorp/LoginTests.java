package com.lavacorp;

import com.lavacorp.db.dao.DatabaseExtension;
import com.lavacorp.models.UserType;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(DatabaseExtension.class)
public class LoginTests {
    public Stream<Arguments> getUsersData() {
        return Stream.of(
                Arguments.of("Hong jun", "minami", UserType.STAFF),
                Arguments.of("Mingy", "SHIKANOKONOKOKOSHITANTAN", UserType.ADMIN)
        );
    }

    @ParameterizedTest
    @Order(0)
    @MethodSource("getUsersData")
    public void testRegister(String username, String password, UserType userType) {
        Login.register(username, password, userType);
    }

    @ParameterizedTest
    @Order(2)
    @MethodSource("getUsersData")
    public void testRepeatRegister(String username, String password, UserType userType) {
        UnableToExecuteStatementException exc = assertThrows(UnableToExecuteStatementException.class, () -> Login.register(username, password, userType));
        assertTrue(exc.getMessage().contains("[SQLITE_CONSTRAINT_UNIQUE]"));
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getUsersData")
    public void testLogin(String username, String password, @SuppressWarnings("unused") UserType userType) {
        assertNotNull(Login.login(username, password));
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getUsersData")
    public void testInvalidLogin(String username, String password, @SuppressWarnings("unused") UserType userType) {
        assertNull(Login.login(username, password + " "));
    }
}
