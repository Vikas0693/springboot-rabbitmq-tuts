Just like in Tut3 we are sending messages to all queue ie. braodcasting here we will be sending to either single or multiple queue based on bindingkey or routing key
1)We are using DirectExchange and while binding queue with this exchange we will use a routing key
2)So all messages that are sent using orange as routing key in Tut4Sender will be sent to autoDeleteQueue1 whereas
3)All messages with key as black will go to both the queues

Run with following command
java -jar target/rabitmq.jar --spring.profiles.active=sender,routing
java -jar target/rabitmq.jar --spring.profiles.active=receiver,routing