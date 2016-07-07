/**
 * Copyright (c) 2016 Mars
 */

package com.one.mongo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * article
 * 
 * @author Jay
 * @since 2016年7月7日
 */
@Document
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String title = "";

	private String content = "";

	private String auth = "";

	private String type = "";
	
	private Date createTime = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Article(String id, String title, String content, String auth, Date createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.auth = auth;
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Article() {
		super();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", auth=" + auth + ", createTime="
				+ createTime + "]";
	}

}
