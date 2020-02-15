package pers.jiangyinzuo.carbon.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jiang Yinzuo
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            String msg = "Hello RabbitMQ!";
            for (int i = 0; i < 5; ++i) {
                channel.basicPublish("", "test001", null, msg.getBytes());
            }
        }
        System.out.println("success");
    }
}
