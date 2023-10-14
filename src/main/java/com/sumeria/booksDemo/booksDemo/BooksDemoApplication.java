package com.sumeria.booksDemo.booksDemo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BooksDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BooksDemoApplication.class, args);
	}

}
