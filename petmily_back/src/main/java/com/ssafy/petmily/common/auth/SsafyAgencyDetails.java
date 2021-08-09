package com.ssafy.petmily.common.auth;

import com.ssafy.petmily.db.entity.agency.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SsafyAgencyDetails implements UserDetails {

    @Autowired
    Agency agency;

    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    List<GrantedAuthority> roles = new ArrayList<>();

    public SsafyAgencyDetails(Agency agency) {
        super();
        this.agency = agency;
    }

    public Agency getAgency() {
        return this.agency;
    }

    @Override
    public String getPassword() {
        return this.agency.getPassword();
    }

    @Override
    public String getUsername() {
        return this.agency.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void setAuthorities(List<GrantedAuthority> roles) {
        this.roles = roles;
    }

}
