package com.course.rabbitmqproducer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.producer.PictureProducer;
import com.course.rabbitmqproducer.producer.entity.Employee;
import com.course.rabbitmqproducer.producer.entity.Picture;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private PictureProducer pictureProducer;
	private final List<String> TYPES = List.of("jpg", "png", "svg");
	private final List<String> SOURCES = List.of("mobile", "web");

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 10; i++) {
			Picture p = new Picture();
			p.setName("Picture" + i);
			p.setSize(ThreadLocalRandom.current().nextLong(1, 10000));
			p.setSource(SOURCES.get(i % SOURCES.size()));
			p.setType(TYPES.get(i % TYPES.size()));
			
			pictureProducer.sendMessage(p);
		}
	}
}
