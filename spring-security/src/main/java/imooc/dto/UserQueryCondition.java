package imooc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserQueryCondition
 *
 * @title: UserQueryCondition
 * @Author shu.shen
 * @Date: 2020/10/23 21:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserQueryCondition {
    private int age;
    private int ageTo;
    private String xxx;
}
