package com.practice.securitydemo1.service;

import com.practice.securitydemo1.domain.Users;
import com.practice.securitydemo1.mapper.UsersMapper;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UsersMapper usersMapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Users users = usersMapper.findByUsername(s);

    if (Objects.isNull(users)) {
      log.info("用戶不存在");
      throw new UsernameNotFoundException("用戶不存在");
    }

    List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins");
    return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths);
  }
}
