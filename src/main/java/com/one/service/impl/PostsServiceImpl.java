package com.one.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.one.main.domain.User;
import com.one.mongo.entity.Post;
import com.one.service.PostService;
import com.one.service.UserService;
import com.one.vo.PostAllVo;
import com.one.vo.PostVo;

/**
* @author	jay
* @since	2016-7-13
*/
@Service
public class PostsServiceImpl implements PostService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void add(PostVo vo) {
		Post post = new Post();
		
		post.setContent(vo.getContent());
		post.setUserId(vo.getUserId());
		post.setLocation(vo.getLocation());
		post.setSource(vo.getSource());
		post.setTime(new Date());
		post.setType(vo.getType());
		
		mongoTemplate.save(post);
	}

	@Override
	public PostVo getById(String id) {
		PostVo post = new PostVo();
		
		Post vo = mongoTemplate.findById(id, Post.class);
		if( vo != null){
			post.setId(vo.getId());
			post.setContent(vo.getContent());
			post.setUserId(vo.getUserId());
			post.setLocation(vo.getLocation());
			post.setSource(vo.getSource());
			post.setTime(new Date());
			post.setType(vo.getType());
		}
		
		return post;
	}

	@Override
	public List<PostVo> get(String userId,String date) {
		List<PostVo> list = new ArrayList<>();

		Query query = new Query();
		
		if(date != null && !date.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			try {
				time = sdf.parse(date);
				query.addCriteria(new Criteria("time").lt(time));	
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		query.addCriteria(new Criteria("userId").is(userId));
		query.with(new Sort(Direction.DESC, "time"));
		query.limit(10);

		List<Post> postList = mongoTemplate.find(query, Post.class);
		
		if(postList!=null && !postList.isEmpty()){
			for (Post vo : postList) {
				PostVo post = new PostVo();
				post.setId(vo.getId());
				post.setContent(vo.getContent());
				post.setUserId(vo.getUserId());
				post.setLocation(vo.getLocation());
				post.setSource(vo.getSource());
				post.setTime(vo.getTime());
				post.setType(vo.getType());
				
				list.add(post);
			}
		}

		return list;
	}

	@Override
	public void deleteById(String id) {
		// delete source TODO
		mongoTemplate.remove(new Query(where("id").is(id)), Post.class);
	}

	@Override
	public List<PostAllVo> getAll(String date) {
		List<PostAllVo> list = new ArrayList<>();

		Query query = new Query();
		
		if(date != null && !date.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			try {
				time = sdf.parse(date);
				query.addCriteria(new Criteria("time").lt(time));	
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		query.addCriteria(new Criteria("type").is(com.one.util.Post.PICTURE));
		query.with(new Sort(Direction.DESC, "time"));
		query.limit(10);

		List<Post> postList = mongoTemplate.find(query, Post.class);
		
		if(postList!=null && !postList.isEmpty()){
			for (Post vo : postList) {
				PostAllVo post = new PostAllVo();
				post.setId(vo.getId());
				post.setContent(vo.getContent());
				post.setUserId(vo.getUserId());
				post.setLocation(vo.getLocation());
				post.setSource(vo.getSource());
				post.setTime(vo.getTime());
				post.setType(vo.getType());
				// 头像 名字
				com.one.mongo.entity.User  user = userService.getById(vo.getUserId());
				post.setPicture(user.getImg());
				post.setName(user.getUsername());
				list.add(post);
			}
		}

		return list;
	}

}
