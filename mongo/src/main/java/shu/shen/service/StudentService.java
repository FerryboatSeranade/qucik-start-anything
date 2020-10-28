package shu.shen.service;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import shu.shen.entity.Student;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加学生
     *
     * @param student
     */
    public void insert(Student student) {
        mongoTemplate.save(student);
    }

    /**
     * 添加学生
     *
     * @return
     */
    public List<Student> queryAll() {
        List<Student> students = mongoTemplate.findAll(Student.class);
        return students;
    }

    /**
     * 批量添加学生
     */
    public void batchInsert() {
        Student student = new Student()
                .setId("1")
                .setName("shushen")
                .setAge(23)
                .setBirthday(new Date());
        mongoTemplate.save(student);
        Query query = Query.query(Criteria.where("name").is("shushen"));
        List<Student> students = mongoTemplate.find(query, Student.class);
    }

    public DeleteResult delete(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        DeleteResult remove = mongoTemplate.remove(query, Student.class);
        return remove;
    }

    public Collection<Student> batchInsert(List<Student> students) {
        Collection<Student> insert = mongoTemplate.insert(students, Student.class);
        return insert;
    }




}
