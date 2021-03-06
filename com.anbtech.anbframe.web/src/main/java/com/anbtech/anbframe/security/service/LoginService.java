/**
 * 
 */
/**
 * @author horang
 *
 */
package com.anbtech.anbframe.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anbtech.anbframe.security.service.domain.Role;
import com.anbtech.anbframe.security.service.domain.User;

@Service
public class LoginService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    LoginDAOService loginDAOService;
  
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {

        logger.info("username : " + username);

        // 회원 정보 dao 에서 데이터를 읽어 옴.
        
        // test 값을 암호화함.
        //String password = "aabcb987e4b425751e210413562e78f776de6285";

        User user = new User();
        user.setUsername(username);
		user = loginDAOService.getUserInfo(username);

        Role role = new Role();
        role.setName("ROLE_USER");

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setAuthorities(roles);

        // 만약 데이터가 없을 경우 익셉션
        //if (user == null) throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");

        return user;
    }
}