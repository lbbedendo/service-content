package io.platosedu.configuration.authentication;

import io.micronaut.security.authentication.UserDetails;

import java.util.Collection;
import java.util.Map;

public class CustomUserDetails extends UserDetails {
    private String id;
    private String email;
    private String cpf;

    public CustomUserDetails(String username, Collection<String> roles) {
        super(username, roles);
    }

    public CustomUserDetails(String username,
                             Collection<String> roles,
                             String id,
                             String email,
                             String cpf,
                             Map<String, Object> attributes) {
        super(username, roles, attributes);
        this.id = id;
        this.email = email;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
