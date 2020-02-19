package by.pochepko.res.spotbot.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public enum Role {USER, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;

    private String password;

    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equal(username, user.username) &&
                Objects.equal(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, role, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", email='" + username + '\'' +
                '}';
    }
}