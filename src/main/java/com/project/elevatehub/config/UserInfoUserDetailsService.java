package com.project.elevatehub.config;

import com.project.elevatehub.model.entities.Users;
import com.project.elevatehub.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

  @Autowired private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users userInfo = usersRepository.findByUserName(username);

    if (null != userInfo) {
      throw new UsernameNotFoundException("user not found " + username);
    }

      return new UserInfoUserDetails(userInfo);
  }
}
