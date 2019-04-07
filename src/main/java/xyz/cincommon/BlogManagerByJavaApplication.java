package xyz.cincommon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages= {"xyz.cincommon.mapper"})
public class BlogManagerByJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogManagerByJavaApplication.class, args);
	}

}

