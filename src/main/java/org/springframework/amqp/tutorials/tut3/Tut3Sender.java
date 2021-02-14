package org.springframework.amqp.tutorials.tut3;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut3Sender {

	@Autowired
	private FanoutExchange fanout;
	
	@Autowired
	private RabbitTemplate template;
	
	AtomicInteger dots = new AtomicInteger(0);
	AtomicInteger count = new AtomicInteger(0);
	
	@Scheduled(initialDelay=1000,fixedDelay=1000)
	public void send() {
		StringBuilder builder = new StringBuilder("Hello");
		if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }
		for (int i = 0; i < dots.get(); i++) {
            builder.append('.');
        }
		builder.append(count.incrementAndGet());
		String message = builder.toString();
		template.convertAndSend(fanout.getName(), "", message);
		System.out.println("Send message [x] :"+message+" at "+ new Date());
		
	}
	
}
