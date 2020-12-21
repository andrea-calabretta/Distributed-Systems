package dsbd2020.lab.ecommdocker.DataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message="the name parameter cannot be blank!!")
    private String name;

    @NotNull(message="the email parameter cannot be blank !!")
    @Column(unique = true)  // nella mia logica email è chiave primaria dunque deve essere unique
    private String email;

    @NotNull(message="the email parameter cannot be blank !!")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
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
