package imooc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import imooc.dto.UserQueryCondition;
import imooc.entity.User;
import imooc.exception.ErrorCode;
import imooc.exception.NoStackInfoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController: 遵循Restful风格
 *
 * @title: UserController
 * @Author shu.shen
 * @Date: 2020/10/23 20:47
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam(value = "username") String nickname, UserQueryCondition userQueryCondition, @PageableDefault Pageable pageable) {
        System.out.println("nickname" + nickname);
        System.out.println("userQueryCondition = " + userQueryCondition);
        System.out.println("pageable = " + pageable);
        List<User> users = new ArrayList<>();
        users.add(new User().setUsername("root1").setPassword("123456"));
        users.add(new User().setUsername("root2").setPassword("1234567"));
        users.add(new User().setUsername("root3").setPassword("12345678"));
        return users;
    }

    //路径变量需要和形参名称匹配以及@PathVaricable的显式值匹配. 能加正则表达式进行参数校验还是很棒的一件事情
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(@PathVariable String id) {
        User user = new User();
        user.setUsername("shushen");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(error -> {
                log.error("my error ----------->: {}", error);
            });
        }
        log.info("user = {}", user);
        user.setId(1);//模拟生成自增主键
        return user;
    }

    @PutMapping(("/{id:\\d+}"))
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String s = fieldError.getField() + "  " + error.getDefaultMessage();
                log.error("my xxx-------------> {}", s);
            });
        }
        log.info("user = {}", user);
        return user;
    }

    @DeleteMapping(("/{id:\\d+}"))
    public void delete(@PathVariable String id) {
        System.out.println("delete user, its id : " + id);
    }

    @GetMapping("/filter")
    @JsonView(User.UserDetailView.class)
    public void filter() {
        log.info("/user/filter start...");
        log.info("/user/filter end...");
    }

    @GetMapping("/interceptorWithoutUnhandledException")
    @JsonView(User.UserDetailView.class)
    public void interceptorWithoutException() {
        log.info("/user/interceptor start...");
        log.info("/user/interceptor end...");
    }

    @GetMapping("/interceptorWithUnhandledException")
    @JsonView(User.UserDetailView.class)
    public void interceptorWithException() throws Exception {
        log.info("/user/interceptor start...");
        throw new NoStackInfoException(ErrorCode.USER_NOT_EXIST,"我是一个异常, /user/interceptor end...");
    }
}
