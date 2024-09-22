package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;

public class User extends NamedEntity {
    private String hashedPassword;
    private Role role;

    public User() {
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public Role getRole() {
        return this.role;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return "User(hashedPassword=" + this.getHashedPassword() + ", role=" + this.getRole() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$hashedPassword = this.getHashedPassword();
        final Object other$hashedPassword = other.getHashedPassword();
        if (this$hashedPassword == null ? other$hashedPassword != null : !this$hashedPassword.equals(other$hashedPassword))
            return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $hashedPassword = this.getHashedPassword();
        result = result * PRIME + ($hashedPassword == null ? 43 : $hashedPassword.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_STAFF
    }
}
