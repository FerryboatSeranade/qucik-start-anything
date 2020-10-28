package imooc.service.impl;

import imooc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * HelloServiceImpl
 *
 * @title: HelloServiceImpl
 * @Author shu.shen
 * @Date: 2020/10/23 23:50
 * @Version 1.0
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public void greeting(String name) {
        log.info("你好 , {}" , name);
    }

}
