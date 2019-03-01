package com.dbs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.user.entity.User;
import com.dbs.user.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
public class UserServiceController {

	@Autowired
	UserServiceImpl userService;
	 
	
    @RequestMapping(value = "/view/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUser(@RequestHeader HttpHeaders headers,@PathVariable Long id){
        User user = userService.searchUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
	
    @RequestMapping(value = "/search/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> searchUser(@RequestHeader HttpHeaders headers, @PathVariable Long id){
    	User user = userService.searchUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestHeader HttpHeaders headers,@RequestBody User user){
    	User savedObject=userService.saveUser(user);
        return new ResponseEntity<User>(savedObject, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestHeader HttpHeaders headers, @PathVariable Long id, @RequestBody User request){
    	
        User user =  userService.searchUser(id);
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPhoto(request.getPhoto());
        user.setDesignation(request.getDesignation());
        
        userService.updateUser(id,user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@RequestHeader HttpHeaders headers, @PathVariable Long id){
    	userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }


	public UserServiceImpl getUserService() {
		return userService;
	}


	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

   
	
    
    
}
