package com.ssafy.petmily.common.auth;

import com.ssafy.petmily.api.service.UserService;
import com.ssafy.petmily.db.entity.agency.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SsafyAgencyDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agency agency = userService.getAgencyByEmail(username);
        if(agency != null) {
            SsafyAgencyDetails agencyDetails = new SsafyAgencyDetails(agency);
            return agencyDetails;
        }
        return null;
    }

}
