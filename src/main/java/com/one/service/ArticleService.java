package com.one.service;

import java.util.List;

import com.one.vo.ArticleVo;

/**
 * @author jay
 * @since 2016年7月7日
 */
public interface ArticleService {

	/**
	 * 新增文章
	 * @param articleVo
	 */
	void add(ArticleVo articleVo);

	/**
	 * 根据id获取文章
	 * @param id
	 * @return
	 */
	ArticleVo getById(String id);

	/**
	 * 根据类型获取文章
	 * @param type
	 * @param data
	 * @param pageSize
	 * @return
	 */
	List<ArticleVo> getByType(String type,String data,Integer pageSize);
	
	List<ArticleVo> getByType(String data);

	/**
	 * 根据id删除文章
	 * @param id
	 */
	void deleteById(String id);

}
