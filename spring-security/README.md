只要添加了依赖,不配置也不注解总之就是啥都不做的时候,都会要求你输入用户名和密码.
![](https://img-blog.csdnimg.cn/20201023021215166.png)


参考慕课网视频:干货满满
了解了 Restful风格的crud && 参数校验(自定义校验)和校验日志的获取 && MockMVC


浏览器和手机app访问同一个url可以得到不同的结果
![](https://img-blog.csdnimg.cn/20201024004246175.png)
具体源代码可以参见BasicErrorController,挺有意思的!
## 服务异常处理:todo

## 拦截的三种方式和各自应用场景
1. Filter
    - 和Servlet相关,底层API
    - 在Spring中直接使用@Component就能生效
    - 或者在配置类中写配置Bean,可以更轻松地配置其生效范围
    - 不知道请求是由哪个controller的哪个API方法处理的
2. Interceptor
    - 属于SpringMVC自己定义的一套东西
    - 光使用@Component无法生效
    - 必须在配置类中进行注册!
    - 可以知道请求是由哪个controler的API方法处理!
3. Aspect
    - 