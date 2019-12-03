package com.example.hw1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Data
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String imgUrl;
    private boolean isActive = true;

    public User(){}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList authorities = new ArrayList<GrantedAuthority>();
        authorities.add(AuthorityUtils.commaSeparatedStringToAuthorityList(role));
        return authorities;
    }

    //We also have to override getPassword but lombok is doing this implicitly (the getter)

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
