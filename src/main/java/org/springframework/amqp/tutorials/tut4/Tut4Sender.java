package org.springframework.amqp.tutorials.tut4;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut4Sender {

	@Autowired
	private DirectExchange direct;
	
	@Autowired
	private RabbitTemplate template;
	
	AtomicInteger dots = new AtomicInteger(0);
	AtomicInteger count = new AtomicInteger(0);
	
	String routingKeys[] = {"orange","black"};
	
	@Scheduled(initialDelay=3000,fixedDelay=5000)
	public void send() {
		StringBuilder builder = new StringBuilder("Hello to");
		if (dots.getAndIncrement() == 2) {
            dots.set(0);
        }
		
		String key = routingKeys[dots.get()];
		builder.append(key).append(' ');
		String message = builder.toString();
		template.convertAndSend(direct.getName(), key, message);
		System.out.println("Send message [x] :"+message+" at "+ new Date()+" to :"+key);
		
	}
	
}
