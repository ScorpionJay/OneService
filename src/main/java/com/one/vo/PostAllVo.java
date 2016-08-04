package com.one.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jay time: 2016年8月4日
 */
public class PostAllVo extends PostVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String picture;

	private String name;

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PostAllVo() {
		super();
	}

	public PostAllVo(String userId, String content, String type, String location, Date time) {
	}

	@Override
	public String toString() {
		return "PostAllVo [picture=" + picture + ", name=" + name + "]";
	}

}
