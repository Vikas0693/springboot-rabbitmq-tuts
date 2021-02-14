package org.springframework.amqp.tutorials.tut3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut3","pub-sub"})
@Configuration
public class Tut3Config {

	Tut3Config(){
		System.out.println("Tut3 config initialized.");
	}
	
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("tut3");
	};
	
	@Profile("receiver")
	public static class ReceiverConfig{
		
		@Bean
		//here spring creates Queue with bean name as autoDeleteQueue1 and we use same name in bindings below so that spring can find one for us
		public Queue autoDeleteQueue1() {
			return new AnonymousQueue();
		}
		
		@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}
		
		@Bean
		public Binding binding1(FanoutExchange fanout,Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
		}
		
		@Bean
		public Binding binding2(FanoutExchange fanout,Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
		}
		
		@Bean
		public Tut3Receiver receiver() {
			return new Tut3Receiver();
		}
	}
	
	@Profile("sender")
	@Bean
	public Tut3Sender sender() {
		return new Tut3Sender();
	}
}
