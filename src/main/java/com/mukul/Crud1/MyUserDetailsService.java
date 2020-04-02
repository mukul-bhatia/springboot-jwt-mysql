package com.mukul.Crud1;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return new User("foo","foo",new ArrayList<>());
//	}
  
	@Autowired
    private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.mukul.Crud1.User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Email " + username + " not found");
		} else {
       //.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
			System.out.println("loadbyusername found");
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        		 new ArrayList<>());
		}
	}
//    private static Collection<? extends GrantedAuthority> getAuthorities(com.mukul.Crud1.User user) {
//        String[] userRoles = user.getRole().stream().map((role) -> role.getName()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }
	
}
