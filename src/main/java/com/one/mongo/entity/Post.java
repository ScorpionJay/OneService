/**
 * Copyright (c) 2016 Mars
 */

package com.one.mongo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * post
 * 
 * @author Jay
 * @since 2016-7-13
 */
@Document
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String userId;
	
	private String content = "";

	private String type = "";

	private List<String> source;

	private String location;

	private Date time;

	public Post() {
		super();
	}

	public Post(String id, String content, String type,  String location, Date time) {
		super();
		this.id = id;
		this.content = content;
		this.type = type;
		this.location = location;
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setSource(List<String> source) {
		this.source = source;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



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



	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", content=" + content + ", type=" + type + ", source="
				+ source + ", location=" + location + ", time=" + time + "]";
	}

	public List<String> getSource() {
		return source;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
