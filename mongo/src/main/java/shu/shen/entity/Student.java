package shu.shen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

//@Document(collection = "article_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Student {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("add_time")
    private Date birthday;

}
