package com.lavacorp.models;

import com.lavacorp.models.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends NamedDatabaseObj {
    private String hashedPassword;
    private boolean isAdmin;

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean comparePassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    @SuppressWarnings("unused")
    public static abstract class UserBuilder<C extends User, B extends UserBuilder<C, B>> extends NamedDatabaseObj.NamedDatabaseObjBuilder<C, B> {
        public B password(String password) {
            this.hashedPassword = passwordEncoder.encode(password);
            return this.self();
        }
    }
}
