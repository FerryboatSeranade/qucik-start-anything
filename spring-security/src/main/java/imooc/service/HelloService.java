package imooc.service;

import org.springframework.stereotype.Service;

/**
 * HelloService
 *
 * @title: HelloService
 * @Author shu.shen
 * @Date: 2020/10/23 23:45
 * @Version 1.0
 */
@Service
public interface HelloService {
    void greeting(String name);
}
