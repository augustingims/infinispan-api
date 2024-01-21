package com.techops.infinispan.api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class InfinispanApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(InfinispanApiApplication.class, args);
	}

}
