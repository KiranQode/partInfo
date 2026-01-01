package com.example.onshapeapp.partInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class PartInfoApplication {

	public static void main(String[] args) {
		// 1. Load .env file from the root directory
        Dotenv dotenv = Dotenv.configure()
                .directory("./") // Explicitly look in the root
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        // 2. Manually set them as System Properties so Spring @Value can see them
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        
		// 3. Now start Spring
		SpringApplication.run(PartInfoApplication.class, args);
	}

}
