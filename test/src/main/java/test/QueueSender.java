package test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class QueueSender
{
    public static void main( String[] args ) throws  Exception
    {
        {
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("my-queue");

            MessageProducer producer=session.createProducer(destination);
            for (int i=0;i<3;i++){
                TextMessage message=session.createTextMessage("message--"+i);
//            Thread.sleep(100);
                //通过消息生产发出消息
                producer.send(message);
            }

            session.commit();
            session.close();
            connection.close();
        }
    }
}
