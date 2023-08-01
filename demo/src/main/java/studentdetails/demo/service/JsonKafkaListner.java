package studentdetails.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import studentdetails.demo.model.Student;
import studentdetails.demo.repository.StudentRepository;

@Service
public class JsonKafkaListner {
    @Autowired
    private StudentRepository studentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaListner.class);

    @KafkaListener(topics = "studentJson", groupId = "myGroup")
    public void consume(Student student) {
        LOGGER.info(String.format("Json message recieved-> %s",student.toString()));
        studentRepository.save(student);
    }
}
