package shu.shen.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shu.shen.entity.Student;

@Repository
public interface StudentDao extends MongoRepository<Student, String> {

}
