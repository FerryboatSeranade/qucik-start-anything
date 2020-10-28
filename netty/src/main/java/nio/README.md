NIO 有两种解释: Non-Blocking IO or New IO
![](https://img-blog.csdnimg.cn/20201018165241241.png)

- 核心概念
    - buffer: 最核心的本质是一个数组,通常字节数组. 实现非阻塞的关键! NIO面向缓冲区/块编程,而BIO则是面向流编程
    - channel: 全双工,一端和buffer交互,另一端和文件或者网络交互.
    - selector: 多路选择器,本质是事件监听器,对一个线程管理多个连接起到了重要的作用. netty事件驱动也是这个缘由.

![](https://img-blog.csdnimg.cn/20201018164745925.png)

- buffer 详解
limit和position, mark, capacity
![](https://img-blog.csdnimg.cn/20201018171359637.png)
  ![](https://img-blog.csdnimg.cn/20201018171616435.png)  
  ![](https://img-blog.csdnimg.cn/20201018171659466.png)
  
- channel详解
最重要的有FileChannel,DatagramChannel,ServerSocketChannel和ServerChannel.
  ![](https://img-blog.csdnimg.cn/20201018172357146.png)
  - 实战1:使用ByteBuffer和FileChannel,将"hello,world!"写入到file01.txt中(如不存在就创建)
    ![](https://img-blog.csdnimg.cn/20201018175849943.png)
  - 实战2: 使用ByteBuffer和FileChannel,读取file01.txt中的内容输出到工作台上    
    ![](https://img-blog.csdnimg.cn/20201018181353643.png)
    
  - 实战3:使用一个Channel来copy file.
    ![](https://img-blog.csdnimg.cn/20201018181501247.png)
//截止[尚硅谷Netty视频教程（2019发布）_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili ](https://www.bilibili.com/video/BV1DJ411m7NR?p=16)
