package com.one.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.one.mongo.entity.User;
import com.one.service.UserService;
import com.one.util.ExceptionUtil;
import com.one.vo.UserVo;

/**
 * @author jay
 * @since 2016年6月8日
 */
@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	ExceptionUtil exceptionUtil;
	
	
	
	@Override
	public com.one.main.domain.User getByUsername(String username) {
		User user = mongoTemplate.findOne(new Query(where("username").is(username)), User.class);
		com.one.main.domain.User outUser = null; 
		if(user != null){
			outUser = new com.one.main.domain.User(user.getUsername(), user.getUsername(), user.getPassword(), user.getAuthorities());
		}

		return outUser;
	}

	@Override
	public Set<UserVo> findAllUsers() {
		List<User> users = mongoTemplate.findAll(User.class);

		Set<UserVo> userVoList = new HashSet<>();

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			UserVo userVo = new UserVo();
			userVo.setId(user.getId());
			userVo.setUserName(user.getUsername());

			userVoList.add(userVo);
		}

		return userVoList;
	}

	@Override
	public void addUser(UserVo userVo) {

		// check userName exist
		com.one.main.domain.User vo  = getByUsername(userVo.getUserName());
		if( vo != null ){
			exceptionUtil.getException("account.exist");
		}
		
		User user = new User();
		user.setUsername(userVo.getUserName());
		user.setPassword(userVo.getPassword());
		user.setAuthorities(userVo.getAuthorities());
		user.setEmail(userVo.getEmail());

		mongoTemplate.save(user);

	}

	@Override
	public UserVo getByUsername2(String username) {
		User user = mongoTemplate.findOne(new Query(where("username").is(username)), User.class);
		UserVo outUser = null; 
		if(user != null){
			outUser = new UserVo();
			outUser.setImg(user.getImg());
			outUser.setUsername(user.getUsername());
			outUser.setName(user.getName());
			outUser.setSign(user.getSign());
			outUser.setGender(user.getGender());
		}

		return outUser;
	}

	@Override
	public void uploadImage(String username, String imgId) {
		Update update = new Update();
		update.set("img", imgId);
		
		mongoTemplate.findAndModify(new Query(where("username").is(username)), update,  User.class);
		
	}

	@Override
	public void sign(String username, String sign) {
		Update update = new Update();
		update.set("sign", sign);
		mongoTemplate.findAndModify(new Query(where("username").is(username)), update,  User.class);
	}

}
