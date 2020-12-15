package org.springframework.amqp.tutorials.tut2;

import java.util.Date;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")//this hello should be same as on which sender send the message
public class Tut2Receiver {

	private final int instance;
	private Long start;
	Tut2Receiver(int instance){
		this.instance = instance;
	}

	@RabbitHandler
	public void receive(String in) throws Exception {
		if(instance==1 && in.matches("Hello.[0-9]+")) {
			System.err.println("Instance 1 has message = "+in+"   **************************");
			throw new AmqpRejectAndDontRequeueException("Exception thrown for instance 1 with message "+in);
			//throw new Exception("Exception thrown for instance 1 with message "+in);
		}
		if(start==null)
			start= System.currentTimeMillis();
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance +
				" [x] Received '" + in + "' at "+new Date());
		doWork(in);
		watch.stop();
		System.out.print("instance " + this.instance +
				" [x] Done in " + watch.getTotalTimeSeconds() + "s" +" at "+new Date());
		long end = System.currentTimeMillis();
		long diff = (end-start)/1000;
		System.out.println("Time taken till now ="+diff+"sec");
	}
	//we are sending messages like Hello.,Hello..,Hello... so based on '.' we assume the task is intensive
	//3 dots will make 3sec wait
	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}
