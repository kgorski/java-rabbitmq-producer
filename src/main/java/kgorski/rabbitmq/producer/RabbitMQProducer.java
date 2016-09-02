package kgorski.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * RabbitMQ Producer.
 * 
 * @author kgorski
 */
public class RabbitMQProducer 
{
    /**
     * Application entry point.
     * 
     * @param args the application arguments
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // Create connection and channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setAutomaticRecoveryEnabled(true);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        // Publish message
        channel.basicPublish("example.exchange", "example.routingkey", MessageProperties.PERSISTENT_TEXT_PLAIN,
            "{\"example\":\"json message\"}".getBytes());
        System.out.println("Message published");

        // Close connection and channel
        channel.close();
        connection.close();
    }
}
