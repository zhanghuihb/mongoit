package com.burton.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void xmlBeanFactory() throws IOException {
		ClassPathResource resource = new ClassPathResource("log4j.properties");
		final InputStream inputStream = resource.getInputStream();
		System.out.println("1");
	}

}
