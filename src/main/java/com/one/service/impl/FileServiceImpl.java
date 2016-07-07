package com.one.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.one.exception.MyException;
import com.one.service.FileService;

/**
 * file service implement
 * 
 * @author Jay
 * @time 2015年6月3日
 */
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	GridFsOperations operations;

	@Override
	public String save(byte[] content, String filename,String contentType) {
		GridFSFile  gf = operations.store(new ByteArrayInputStream(content), filename,contentType);
		return gf.getId().toString();
	}

	@Override
	public byte[] getByName(String fileName) {
		byte[] result = null;
		try {
			GridFSDBFile file = operations.findOne(Query.query(GridFsCriteria
					.whereFilename().is(fileName)));
			InputStream inputStream = file.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			
		
			while (inputStream.read(buffer) != -1)
				outStream.write(buffer);
			outStream.flush();
			result = outStream.toByteArray();
			inputStream.close();
			outStream.close();
		} catch (Exception e) {
			//throw new MyException(301, "文件不存在！");
		}

		return result;
	}

	@Override
	public void deleteById(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getById(String id,HttpServletResponse response) {

        byte[] result = null;
		try {
			GridFSDBFile file = operations.findOne(Query.query(GridFsCriteria.where("_id").is(id)));
			response.setContentType(file.getContentType());
			InputStream inputStream = file.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];

			while (inputStream.read(buffer) != -1)
				outStream.write(buffer);
			outStream.flush();
			result = outStream.toByteArray();
			inputStream.close();
			outStream.close();
			
			OutputStream os = response.getOutputStream();
			if(null != result){
				os.write(result); 
			}
			
		} catch (Exception e) {
			throw new MyException(301, "文件不存在！");
		}

	}

}
