package imooc.entity;

import com.fasterxml.jackson.annotation.JsonView;
import imooc.validate.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * User
 * @title: User
 * @Author shu.shen
 * @Date: 2020/10/23 20:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer id;
    @MyConstraint(message = "我的自定义验证测试~~~")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日只能填写过去的时间")
    private Date birthday;
    //JSonView 使用三步: 1.使用接口声明视图 2.在值对象的get方法上指定视图 3.在Controller方法上指定视图
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

}

