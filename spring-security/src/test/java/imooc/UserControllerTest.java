package imooc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMVC;

    @Before
    public void setup() {
        mockMVC = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 条件查询
     * @throws Exception
     */
    @Test
    public void whenQuerySuccess() throws Exception {
        ResultActions perform= mockMVC.perform(
                MockMvcRequestBuilders.get("/user")
                        .param("username", "shushen")
                        .param("age", "18")
                        .param("ageTo", "80")
                        .param("xxx", "yyy")
                        .param("size", "15")
                        .param("page", "3")
                        .param("sort", "age,desc")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //有兴趣看下github上jsonPath项目
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);//预期只能显示简单视图(不含密码)
    }

    /**
     * 主键查询 : 成功case
     * @throws Exception
     */
    @Test
    public void whenGetInfoSuccess() throws Exception {
        ResultActions  perform = mockMVC.perform(
                MockMvcRequestBuilders.get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("shushen"));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);//预期可以显示复杂视图(包含密码)
    }

    /**
     * 主键查询 : 失败case
     * @throws Exception
     */
    @Test
    public void whenGetInfoFail() throws Exception {
        mockMVC.perform(
                MockMvcRequestBuilders.get("/user/a")//参数校验不通过,期望失败
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    /**
     * insert
     * @throws Exception
     */
    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        long time = date.getTime();
        String userContent="{\"username\":\"shushen\",\"password\":null,\"birthday\":"+ time + "}";//参数校验失败:密码不许为空,@Valid和配套注解共同使用! 但是错误日志如何记录呢???
        System.out.println("userContent = " + userContent);
        ResultActions perform = mockMVC.perform(
                MockMvcRequestBuilders.post("/user")//参数校验不通过,期望失败
                        .content(userContent)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);

    }

    /**
     * update
     * @throws Exception
     */
    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date();
        final long YEAR = TimeUnit.DAYS.toMillis(1);
        long futureTime = date.getTime()+ 3* YEAR;
        String userContent="{\"id\":1,\"username\":\"shu.shen.bak\",\"password\":null,\"birthday\":"+ futureTime + "}";//参数校验失败:密码不许为空,@Valid和配套注解共同使用! 但是错误日志如何记录呢???
        System.out.println("userContent = " + userContent);
        ResultActions perform = mockMVC.perform(
                MockMvcRequestBuilders.put("/user/1")//参数校验不通过,期望失败
                        .content(userContent)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);

    }

    /**
     * delete
     * @throws Exception
     */
    @Test
    public void whenDeleteSuccess() throws Exception {
        ResultActions perform = mockMVC.perform(
                MockMvcRequestBuilders.delete("/user/1")//参数校验不通过,期望失败
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);

    }

    /**
     * filter测试： https://stackoverflow.com/questions/47290024/spring-unit-test-mockmvc-fails-when-using-custom-filter-in-spring-security
     * 我本来想测试下filter的init和destroy方法，可惜失败了. 我意识到MockMVC并不能用来测试spring容器本身！
     */
    @Test
    public void filter() throws Exception {
        ResultActions perform = mockMVC.perform(
                MockMvcRequestBuilders.get("/user/filter")//参数校验不通过,期望失败
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);
    }

    /**
     * interceptor
     * @throws Exception
     */
    @Test
    public void interceptor() throws Exception {
        ResultActions perform = mockMVC.perform(
                MockMvcRequestBuilders.get("/user/interceptor")//参数校验不通过,期望失败
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        log.info(contentAsString);
    }
}
