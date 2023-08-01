package studentdetails.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentdetails.demo.model.Student;
import studentdetails.demo.repository.StudentRepository;
import studentdetails.demo.service.JsonKafkaListner;
import studentdetails.demo.service.JsonKafkaProducer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/students")
public class JsonMessageController {
    @Autowired
    private StudentRepository studentRepository;
    private JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping(path = "/get-student-details")
    public List<Student> getStudentDetails() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/get-student-by-id/{id}")
    public Optional<Student> getStudentDetails(@PathVariable("id") String id) {
        return studentRepository.findById(id);
    }

    @PostMapping(path = "/create-student")
    public ResponseEntity<String> publish(@RequestBody Student student){
        kafkaProducer.sendMessageToTopic(student);
        return ResponseEntity.ok("Json message sent to kafka");
    }

    @DeleteMapping(path="/delete-student/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id")  String id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
    @PutMapping(path = "/update-student-by-id/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") String id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student currentStudent = optionalStudent.get();
            currentStudent.setBranch(updatedStudent.getBranch());
            currentStudent.setGpa(updatedStudent.getGpa());
            kafkaProducer.sendMessageToTopic(currentStudent);
            return ResponseEntity.ok("Json message sent to kafka");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
