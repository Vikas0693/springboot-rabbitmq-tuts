This is a tutorial on Publish Subscribe idea
*How its is different from Tut2 i.e WorkQueue
1)Sender does not send messages to queue
2)Send sends messages to exchange
3)Exchange send message to all queue connected with it through bindingKey
4)We are using FanoutExchange which we configured in Tut3Config
5)We have created binding1 and binding2 which Exchange gets bind to

To Run use 'spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=pub-sub,sender' in Maven Goal