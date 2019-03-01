package com.dbs.user.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbs.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User , Long> {
	
	@Query("SELECT a.userId FROM user a WHERE a.userId=:userId")
	String  fetchByUserId(@Param("userId") String userId);
	
	@Query("SELECT a.userId FROM user a WHERE a.userId=:userId and a.pwd=:pwd")
	String  verifyLogin(@Param("userId") String userId,@Param("pwd") String pwd);
	
}