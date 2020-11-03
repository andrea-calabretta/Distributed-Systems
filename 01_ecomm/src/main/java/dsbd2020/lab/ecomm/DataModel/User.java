package dsbd2020.lab.ecomm.DataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull (message = "the name cannot be a blank field")
    private String name;

    @NotNull (message = "the email cannot be a blank field")
    @Column(unique = true) //la mail la usiamo come PRIMARY KEY essendo unica
    private String email;

    @NotNull (message = "the psw cannot be a blank field")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //affinchè non sia possibile leggere la password
    private String psw;

    @ElementCollection
    private List<String> roles;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPsw() {
        return psw;
    }

    public User setPsw(String psw) {
        this.psw = psw;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public User setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                ", roles=" + roles +
                '}';
    }
}
