package com.dbs.user.controller.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.dbs.api.gateway.JWTAuthentication;
import com.dbs.user.controller.UserServiceController;
import com.dbs.user.entity.User;
import com.dbs.user.repository.UserRepository;
import com.dbs.user.service.UserServiceImpl;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class UserServiceControllerTest {

	
	MockHttpServletRequest httpServletRequest;
	
	HttpHeaders httpHeaders;
   
	MockHttpServletResponse httpServletResponse;
	
	UserServiceImpl userService;
		
	UserServiceController controller;
	
	@Mock
	UserRepository userRepository;
	
	public String ACCESS_TOKEN="";
	
	JWTAuthentication jwt;
	
	@Before
	public void setup() throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
	
		
		// AbstractApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		 //UserRepository repository = context.getBean(UserRepository.class);
	        
		 jwt= new JWTAuthentication();
		 httpHeaders= new HttpHeaders();
		 httpServletRequest = new MockHttpServletRequest();
		 httpServletResponse = new MockHttpServletResponse();
		 userService= new UserServiceImpl();
		 userService.setUserRepository(userRepository);
		
		 controller = new UserServiceController();
		 controller.setUserService(userService);
		 //controller.setRepository(repository);
		 /*Calling login authentication API which is called in ZUUL API gateway
		  to get Token for subsequent call. User add back the token in header 
		  and call other API calls
		 */
		  ACCESS_TOKEN=jwt.get("test");
		 
		
	}
	
	
	/*This token verify API will be implemented in ZUUL API gateway.
	All request will be verified using the ACCESS_TOKEN before reaching the actual API call
	*/
	@Test
	public void test1_verifyToken() throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
		jwt.verify(ACCESS_TOKEN);
	}
	
	@Test
	public void test2_saveUser(){
		Mockito.when(userRepository.save(Matchers.anyObject())).thenReturn(getUserDetails());
		ResponseEntity<User> response=controller.saveUser(httpHeaders, getUserDetails());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void test3_updateUser(){
		User user = getUserDetails();
		Optional<User> userOptional = Optional.of(user);
		Mockito.when(userRepository.findById(Matchers.anyObject())).thenReturn(userOptional);
		Mockito.when(userRepository.save(Matchers.anyObject())).thenReturn(getUserDetails());
		ResponseEntity<User> response=controller.updateUser(httpHeaders, new Long(1),getUserDetails());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void test4_searchUser(){
		User user = getUserDetails();
		Optional<User> userOptional = Optional.of(user);
		Mockito.when(userRepository.findById(Matchers.anyObject())).thenReturn(userOptional);
		ResponseEntity<User> response=controller.searchUser(httpHeaders, new Long(1));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void test4_deleteUser(){
		
		Mockito.doNothing().when(userRepository).deleteById(Matchers.anyObject());
		ResponseEntity<String> response=controller.delete(httpHeaders, new Long(1));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
		
	public User getUserDetails(){
		User user= new User();
		user.setFirstName("Sundar");
		user.setEmail("abc@test.com");
		user.setPhone("+6512343434");
		user.setDesignation("Admin");
		user.setPhoto(getImage());
		return user;
	}
	
	public Image getImage(){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("/image/JavaImage.jpg"));
		} catch (IOException e) {
		}
		return img;
	}
	
	
	
	
}
