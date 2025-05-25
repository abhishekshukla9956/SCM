package com.SmartContactManager.SCM.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    
    @Id
    private String userId;
    @Column(name="user_name",nullable=false)
    private String name;
    @Column(name="user_email",nullable=false,unique=true)
    private String email;
    private String password;
    private String phone;

    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //SELF,google,facebook
    @Enumerated(value = EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;


     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
     private List<Contact> contacts =new ArrayList<>();

     @ElementCollection(fetch = FetchType.EAGER)
     private List <String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

     Collection <SimpleGrantedAuthority> roles= roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return  roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
