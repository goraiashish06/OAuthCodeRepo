package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthUserDetail;
import com.example.demo.model.User;
import com.example.demo.repository.UserDetailRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionaluser = userDetailRepository.findByUsername(username);
		// optionaluser.orElseThrow(()-> UsernameNotFoundException("Incorrect Username
		// or password"));
		UserDetails userDetails = new AuthUserDetail(optionaluser.get());
		// check the expiration of the user, whether it is valid or not
		new AccountStatusUserDetailsChecker().check(userDetails);

		return userDetails;
	}

	private Object UsernameNotFoundException(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
