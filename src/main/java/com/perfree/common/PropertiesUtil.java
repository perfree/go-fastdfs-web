package com.perfree.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取properties工具类
 * @author Perfree
 *
 */
@Component
public class PropertiesUtil {

	@Value("${project.is.jar}")
	private Boolean isJar;
	
	/**
	 * 动态的获取配置文件信息,打成jar包之后获取根目录config下的server.properties配置文件
	 * @return File
	 */
	public File getProperties() {
		if(isJar) {
			return new File("config/server.properties");
		}else {
			return new File(getClass().getClassLoader().getResource("server.properties").getFile());
		}
		
	}
	
	/**
	 * 获取配置文件属性值
	 * @param name(属性名)
	 * @return String
	 */
	public String getProperty(String name) {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		String value = null;
		try {
			fileInputStream = new FileInputStream(this.getProperties());
			properties.load(fileInputStream);
			value = properties.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
