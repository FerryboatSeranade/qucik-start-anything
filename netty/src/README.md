netty由jboss提供的一个开源框架,它是一个异步的事件驱动的网络框架.
要透彻理解它需要先学习nio. 
## Introduction
- 同步和异步: 线程间的依赖,调用线程必须等待被调用线程执行代码结束还是先略过等其主动回调
- 阻塞和非阻塞: 线程本身的角度,是否等待缓冲区
- BIO(Blocking IO)&&NIO(Non-Blocking IO)&&AIO(Asynchronized IO)
    - BIO: 同步阻塞 即传统IO
      ![](https://img-blog.csdnimg.cn/20201018155732985.png)
    - NIO: 同步非阻塞(采用轮询) 
      ![](https://img-blog.csdnimg.cn/20201018160002297.png)
    - AIO: 异步非阻塞(异步就是为了解决阻塞,所以不存在异步阻塞)
    ![](https://img-blog.csdnimg.cn/20201018160234403.png)

- 应用场景
    - 分布式系统中各个节点需要通信,即RPC/RMI,在RPC框架中就要用到netty,比如dubbo就采取了netty作为基础通信组件
    - 游戏行业方便开发私有协议栈增强安全,地图服务器中使用可以方便高性能通信
    - hadoop的高性能序列化和通信RPC框架avro默认采用netty进行跨节点通信
    - 其他开源项目:Akka,Flink,Spark

[8分钟深入浅出搞懂BIO、NIO、AIO - 知乎 ](https://zhuanlan.zhihu.com/p/83597838)
[尚硅谷Netty视频教程（2019发布）_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili ](https://www.bilibili.com/video/BV1DJ411m7NR?p=2)