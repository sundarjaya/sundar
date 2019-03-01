package com.dbs.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dbs.user.entity.User;
import com.dbs.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 
	 @RequestMapping(value = "/view/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public User findByID(Long id){
	     return userRepository.findById(id).get();
	 }
		
	 public User searchUser(Long id){
	       return userRepository.findById(id).get();
	 }
	 
	 public User saveUser(User user){
	       return userRepository.save(user);
	 }
	 
	 public User updateUser(Long Id,User obj){
		 
		 Optional<User> userOptional = userRepository.findById(Id);
	     User user = userOptional.get();
	     user.setFirstName(obj.getFirstName());
	     user.setEmail(obj.getEmail());
	     user.setPhone(obj.getPhone());
	     user.setPhoto(obj.getPhoto());
	     user.setDesignation(obj.getDesignation());
	     
	     return userRepository.save(user);
	 }
	 
	 public void deleteUser(Long Id){
	      userRepository.deleteById(Id);
	 }

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	 
	 
}
