package org.svenehrke.springcmdlinejooq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCmdlineJooqApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCmdlineJooqApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("RUNNING");
	}
}
