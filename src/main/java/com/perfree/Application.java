package com.perfree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.util.Date;

/**
 * 启动类
 * @author Perfree
 *
 */
@MapperScan("com.perfree.mapper")
@SpringBootApplication
public class Application implements CommandLineRunner {
	@Value("${server.port}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(new Date() + ", " + address + ":"+ serverPort +" >>>>>>>>已启动完成...");
	}
}
