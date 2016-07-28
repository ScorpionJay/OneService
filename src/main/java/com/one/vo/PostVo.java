/**
 * Copyright (c) 2016 Mars
 */

package com.one.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * post
 * 
 * @author Jay
 * @since 2016年7月13日
 */
public class PostVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String userId;

	private String content = "";

	private String type = "";

	private List<String> source;

	private String location;

	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public List<String> getSource() {
		return source;
	}

	public void setSource(List<String> source) {
		this.source = source;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PostVo() {
		super();
	}

	public PostVo(String userId, String content, String type, String location, Date time) {
		this.userId = userId;
		this.content = content;
		this.type = type;
		this.location = location;
		this.time = time;
	}

	@Override
	public String toString() {
		return "PostVo [id=" + id + ", userId=" + userId + ", content=" + content + ", type=" + type + ", source="
				+ source + ", location=" + location + ", time=" + time + "]";
	}


}
