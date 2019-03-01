package com.dbs.user.service;

import org.springframework.stereotype.Service;

import com.dbs.user.entity.User;

@Service
public interface UserService {

	 public User findByID(Long id);
	 public User searchUser(Long id);
	 public User saveUser(User user);
	 public User updateUser(Long Id,User obj);
	 public void deleteUser(Long Id);

	 
}
