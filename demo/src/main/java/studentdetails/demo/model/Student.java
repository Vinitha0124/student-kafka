package studentdetails.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "student")
public class Student {
    @Id
    private  String id;
    private  String name;

    private LocalDate dob;
    private String branch;
    private Float gpa;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }


    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float marks) {
        if(marks>=0 || marks<=10) {
            gpa = marks;
        }
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
