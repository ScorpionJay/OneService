/**
 * Copyright (c) 2016 Mars
 */

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

import com.one.mongo.entity.Article;
import com.one.service.ArticleService;
import com.one.vo.ArticleVo;

/**
 * @author Jay
 * @since 2016年7月7日
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void add(ArticleVo articleVo) {
		Article article = new Article();
		article.setTitle(articleVo.getTitle());
		article.setContent(articleVo.getContent());
		article.setAuth(articleVo.getAuth());
		article.setCreateTime(articleVo.getCreateTime());
		article.setType(articleVo.getType());
		mongoTemplate.save(article);
	}

	@Override
	public ArticleVo getById(String id) {
		Article article = mongoTemplate.findById(id, Article.class);
		ArticleVo articleVo = new ArticleVo();
		articleVo.setTitle(article.getTitle());
		articleVo.setContent(article.getContent());
		articleVo.setAuth(article.getAuth());
		articleVo.setCreateTime(article.getCreateTime());
		articleVo.setType(article.getType());
		return articleVo;
	}

	@Override
	public List<ArticleVo> getByType(String type, String data, Integer pageSize) {
		List<ArticleVo> list = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		try {
			time = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Query query = new Query();
		query.addCriteria(new Criteria("createTime").lt(time));	
		query.addCriteria(new Criteria("type").is(type));
		query.with(new Sort(Direction.DESC, "createTime"));
		query.limit(pageSize);

		List<Article> articleList = mongoTemplate.find(query, Article.class);
		
		if(articleList!=null && !articleList.isEmpty()){
			for (Article article : articleList) {
				ArticleVo articleVo = new ArticleVo();
				articleVo.setTitle(article.getTitle());
				articleVo.setContent(article.getContent());
				articleVo.setAuth(article.getAuth());
				articleVo.setCreateTime(article.getCreateTime());
				articleVo.setType(article.getType());
				
				list.add(articleVo);
			}
		}

		return list;
	}

	@Override
	public void deleteById(String id) {
		mongoTemplate.remove(new Query(where("id").is(id)), Article.class);
	}

}
