package ua.com.codefire.ecommerce.data.entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ankys on 10.02.2017.
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role_name", unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    })
    private List<User> userList;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return getId().equals(role.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
