package studentdetails.demo.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import studentdetails.demo.model.Student;
@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    private KafkaTemplate<String, Student> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Student> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic(Student data) {
        LOGGER.info(String.format("Message sent--> %s",data.toString()));
        Message<Student> message  = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "studentJson")
                .build();
        kafkaTemplate.send(message);
    }

}
