package com.one.service;

import java.util.Set;

import com.one.main.domain.User;
import com.one.vo.UserVo;

/**
 * @author jay
 * @since 2016年6月8日
 */
public interface UserService {

	
	/**
	 * 新增用户
	 * @param user
	 */
	void addUser(UserVo userVo);
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	User getByUsername(String username);
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	UserVo getByUsername2(String username);
	
	/**
	 * 查找所有用户
	 * @return
	 */
	Set<UserVo> findAllUsers();
	
	/**
	 * 上传头像
	 * @param username
	 * @param imgId
	 */
	void uploadImage(String username,String imgId);
	
	/**
	 * 个性签名
	 * @param username
	 * @param sign
	 */
	void sign(String username, String sign);
	
}
