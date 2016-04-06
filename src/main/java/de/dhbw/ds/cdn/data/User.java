package de.dhbw.ds.cdn.data;


import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.UUID;

@Table(value = "users")
public class User {

    @PrimaryKeyColumn(name = "id",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    @Column(value = "name")
    private String name;

    @PrimaryKeyColumn(name="login",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String login;

    @Column(value = "password")
    private String password;

    @Column(value = "roles")
    private ArrayList<String> rights;

    public User() {
    }

    public User(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.rights = user.getRights();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getRights() {
        return rights;
    }

    public void setRights(ArrayList<String> rights) {
        this.rights = rights;
    }


}