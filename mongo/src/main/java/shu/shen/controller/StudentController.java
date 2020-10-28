package shu.shen.controller;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.shen.entity.Student;
import shu.shen.service.StudentService;

import java.util.Collection;
import java.util.List;

/**
 * @author shu.shen
 * [Spring Boot中快速操作Mongodb ](https://juejin.im/post/6844903606399795208)
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public void insert(@RequestBody Student student) {
        studentService.insert(student);
    }

    @GetMapping
    public List<Student> queryAll() {
        return studentService.queryAll();
    }

    @DeleteMapping("/{id}")
    public DeleteResult delete(@PathVariable("id") String id) {
        DeleteResult delete = studentService.delete(id);
        return delete;
    }

    @PostMapping("/list")
    public Collection<Student> batchInsert(@RequestBody List<Student> students){
        Collection<Student> students1 = studentService.batchInsert(students);
        return students1;
    }

}
