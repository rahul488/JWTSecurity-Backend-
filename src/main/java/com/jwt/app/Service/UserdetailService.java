package com.jwt.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.app.Dao.UserDao;
import com.jwt.app.Entity.User;

@Service
public class UserdetailService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user=userDao.findByEmail(email);
		
		if(user == null) throw new UsernameNotFoundException("User Not Found");
		
		
		return new UserPrinciple(user);
	}

}
