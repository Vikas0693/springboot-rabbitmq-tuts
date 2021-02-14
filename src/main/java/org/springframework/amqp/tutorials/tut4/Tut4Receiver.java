package org.springframework.amqp.tutorials.tut4;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class Tut4Receiver {
	
	//autoDeleteQueue1 has binding key of orange and black as in Tut4Config
	@RabbitListener(queues= "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException{
		receive(1,in);
	}
	
	//autoDeleteQueue2 has binding key of black as in Tut4Config
	@RabbitListener(queues= "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException{
		receive(2,in);
	}
	
	private void receive(int queueNumber, String message) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("Instance "+queueNumber+" [x] Received "+message+" at "+new Date());
		doWork(message);
		watch.stop();
		System.out.println("Instance "+queueNumber+" [x] Done at "+watch.getTotalTimeSeconds());
	}
	
	 private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }

}
