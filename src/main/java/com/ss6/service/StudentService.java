package com.ss6.service;

import com.ss6.entity.Student;
import com.ss6.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//Create by Quynh at 2:30 CH on 20/05/2026
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student){
        student.setId(null);
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student studentDetails){
        return studentRepository.findById(id).map(existsStu->{
            existsStu.setFullName(studentDetails.getFullName());
            existsStu.setEmail(studentDetails.getEmail());
            existsStu.setGpa(studentDetails.getGpa());
            return studentRepository.save(existsStu);
        });
    }

    public boolean deleteStudent(Long id){
       if(studentRepository.existsById(id)){
           studentRepository.deleteById(id);
           return true;
       }
       return false;
    }

    public Optional<Student> patchStudent(Long id, Map<String,Object> updates){
        return studentRepository.findById(id).map(exitsStu->{
            updates.forEach((k,v)->{
                switch(k){
                    case "fullName":
                        exitsStu.setFullName((String) v);
                        break;
                    case "email":
                        exitsStu.setEmail((String) v);
                        break;
                    case "gpa":
                        exitsStu.setGpa((Double.valueOf(v.toString())));
                        break;
                }
            });

            return  studentRepository.save(exitsStu);
        });
    }
}
