package org.example.homework.controller;

import org.example.homework.entity.Student;
import org.example.homework.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/")
    public Object getAllStudents() {
        List<Student> students=studentMapper.selectList(null);
        return students;
    }
    @PostMapping("/")
    public Object insertStudents(@RequestBody Student student){
        studentMapper.insert(student);
        return student;
    }
    @DeleteMapping("/")
    public Object deleteStudent(Long id){
        studentMapper.deleteById(id);
        return "delete success";
    }
    @DeleteMapping("/batch-delete")
    public Object batchDelete(@RequestBody Long[]ids){
        studentMapper.deleteByIds(List.of(ids));
        return "delete success";
    }
    @PutMapping("/")
    public Object updateStudent(@RequestBody Student student){
        studentMapper.updateById(student);
        return student;
    }
}
