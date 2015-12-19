package test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Jack on 2015/12/19.
 */
public class QueueReceiver {
    public static  void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue("my-queue");

        MessageConsumer consumer=session.createConsumer(destination);
        int i=0;
        while (i<3){
            i++;
            TextMessage message=(TextMessage)consumer.receive();
            session.commit();
            System.out.println("收到消息:"+message.getText());
        }
        session.close();
        connection.close();
    }
}
