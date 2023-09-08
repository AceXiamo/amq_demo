package com.axm.consumer;

import com.axm.config.AMQConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: AceXiamo
 * @ClassName: AMQListener
 * @Date: 2023/9/8 18:13
 */
@Component
public class AMQListener {

    @RabbitListener(queues = AMQConfiguration.dieQueueName)
    public void receiveMessage(String message) {
        System.out.println("[" + message + "] receive at: " + new Date());
    }


}
