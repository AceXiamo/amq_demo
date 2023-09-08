package com.axm.web;

import com.axm.config.AMQConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: AceXiamo
 * @ClassName: AMQController
 * @Date: 2023/9/8 18:07
 */
@RestController
@RequestMapping("/amq")
public class AMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(@RequestParam(name = "msg") String msg) {
        Message message = MessageBuilder.withBody(msg.getBytes()).build();
        // 单位: ms
        message.getMessageProperties().setExpiration(String.valueOf(10 * 1000));
        rabbitTemplate.send(AMQConfiguration.exchangeName, AMQConfiguration.routingKey, message);
        System.out.println("[" + msg + "] send at: " + new Date());
        return "send";
    }

}
