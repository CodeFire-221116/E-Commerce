package ua.com.codefire.ecommerce.data.entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ankys on 10.02.2017.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name", unique = true)
    private String username;
    // MD5 HASH
    @Column(name = "user_pass", length = 32)
    private String password;
    @Column(name = "user_email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    })
    private List<Role> roleList;

    public User() {
    }

    public User(String username, String notEncryptedPassword) {
        this.username = username;
        this.password = DigestUtils.md5Hex(notEncryptedPassword);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String notEncryptedPassword) {
        return DigestUtils.md5Hex(notEncryptedPassword).equals(password);
    }

    /**
     * function for updating password by user
     * @param notEncryptedPassword New not encrypted password.
     */
    public void updatePassword(String notEncryptedPassword) {
        this.password = DigestUtils.md5Hex(notEncryptedPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
