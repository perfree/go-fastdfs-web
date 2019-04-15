package com.perfree.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 读取properties工具类
 * @author Perfree
 *
 */
public class PropertiesUtil {

	public String getProperty(String name) {
		Properties properties = new Properties();
		Resource resource = new ClassPathResource("server.properties");
		FileInputStream fileInputStream = null;
		String value = null;
		try {
			fileInputStream = new FileInputStream(resource.getFile());
			properties.load(new FileInputStream(resource.getFile()));
			value = properties.getProperty(name);
		} catch (Exception e) {
			
		}finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
