package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
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

    @EqualsAndHashCode.Exclude
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean comparePassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public static abstract class UserBuilder<C extends User, B extends UserBuilder<C, B>> extends NamedDatabaseObj.NamedDatabaseObjBuilder<C, B> {
        public B password(String password) {
            this.hashedPassword = passwordEncoder.encode(password);
            return this.self();
        }
    }
}
