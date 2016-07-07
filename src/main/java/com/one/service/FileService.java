package com.one.service;

import javax.servlet.http.HttpServletResponse;

/**
 * File接口
 * @author 		Jay
 * @time		2015年6月3日
 */
public interface FileService {
	
	String save(byte[] content, String filename,String contentType);
	
	byte[] getByName(String fileName);
	
	void getById(String id,HttpServletResponse response);
	
	void deleteById(String id);
	
}

