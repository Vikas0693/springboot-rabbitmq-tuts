package org.springframework.amqp.tutorials.tut1;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

	@Autowired
	RabbitTemplate template;
	
	@Autowired
	Queue queue;
	
	@Scheduled(fixedDelay=5000,initialDelay=5000)
	public void send() {
		String message = "Hello World at :"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		this.template.convertAndSend(queue.getName(), message);
		System.out.println("[x] Sent '"+message+"'");
	}
	
}
