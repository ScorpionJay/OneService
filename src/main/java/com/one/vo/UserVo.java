package com.one.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jay
 * @since 2016年6月7日
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private String authorities;

	/**
	 * 0 男 2 女
	 */
	private Integer gender = 0;

	/**
	 * 真实姓名
	 */
	private String name = "";

	/**
	 * 签名
	 */
	private String sign = "";

	/**
	 * 头像
	 */
	private String img = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public UserVo() {
	}

	public UserVo(String id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", userName=" + username + ", password=" + password + ", email=" + email + "]";
	}

	public UserVo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserVo(String username, String password, String authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
