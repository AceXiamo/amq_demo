package com.axm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: AceXiamo
 * @ClassName: AMQConfiguration
 * @Date: 2023/9/8 17:53
 */
@Configuration
public class AMQConfiguration {

    public final static String queueName = "testQueue";
    public final static String exchangeName = "testExchange";
    public final static String routingKey = "myRoutingKey";
    public final static String dieQueueName = "dieQueue";
    public final static String dieExchangeName = "dieExchange";
    public final static String dieRoutingKey = "deadRoutingKey";


    @Bean
    public Queue queue() {
        Queue queue = new Queue(queueName);
        queue.addArgument("x-dead-letter-exchange", dieExchangeName);
        queue.addArgument("x-dead-letter-routing-key", dieRoutingKey);
        return queue;
    }

    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange myExchange) {
        return BindingBuilder.bind(queue).to(myExchange).with(routingKey);
    }

    @Bean
    public Queue dieQueue() {
        return new Queue(dieQueueName);
    }

    @Bean
    public DirectExchange dieExchange() {
        return new DirectExchange(dieExchangeName);
    }

    @Bean
    public Binding deadLetterBinding(Queue dieQueue, DirectExchange dieExchange) {
        return BindingBuilder.bind(dieQueue).to(dieExchange).with("deadRoutingKey");
    }


}
