package studentdetails.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studentdetails.demo.model.Student;

public interface StudentRepository extends MongoRepository<Student,String> {
}
