# 建立这个仓库的目的
``
平时在做项目的时候，难免会有些知识点不熟悉或者是平时看视频的时候
练习，因此测试都放在这个仓库里面
``
1. testreference 是测试强软弱虚引用的包，TestReference这个类测试了强软弱虚
-Xmx20m这是设置虚拟机参数，方便测试: 参照链接[强软弱虚四大应用实战](https://juejin.im/post/6844904085091516430)
2. 项目引入了lombok方便测试

## 获取到main路径下的文件
>     Resource resource = new DefaultResourceLoader().getResource("classpath:application.yaml");
用于获取main>resource>下的application.yaml文件，和test不一样

##使用hutools生产雪花ID
```java
    @Test
    public void get() {
        while (true) {
            System.out.println(GenerateSnow());
        }
    }

    public static long GenerateSnow() {
        long workerId = generateRandom();//为终端ID
        long datacenterId = 1;//数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    private static long generateRandom() {
        double v = Math.random() * 128.0D;
        return (long) v;
    }
```
## Webclient 使用
[WebClient使用](https://blog.csdn.net/iteye_13139/article/details/82726588)\

## Reactor的publishOn和subscribeOn的区别
> publishOn 和 subscribeOn。这两个方法的作用是指定执行 Reactive Streaming 的 Scheduler（可理解为线程池）， 不过这两个方法是用的场景是不相同的.
  为何需要指定执行 Scheduler 呢？一个显而易见的原因是：
  组成一个反应式流的代码有快有慢，可能你的下游接口RT是不同的。如果将这些功能都放在一个线程里执行，快的就会被慢的影响，
  所以需要相互隔离。这是这两个方法应用的最典型的场景。

实际用途
这里介绍 publishOn和 subscribeOn的一种实际用途，那就是反应式编程和传统的，会导致线程阻塞的编程技术混用的场景。其实开头两个例子已经解释了这个场景。

在第一个 publishOn 的例子中，repository::save 会导致线程阻塞，为了避免造成对其它反应式操作的影响，便使用 publishOn 改变其执行线程。

在第二个 subscribeOn 的例子中，repository.findAll() 会导致线程阻塞。但是其是源头的 publisher，因此不能使用 publishOn 改变其 执行线程。这时就需要使用 subscribeOn，在源头上修改其执行线程。

这样，通过 publishOn 和 subscribeOn 就在反应式编程中实现了线程池隔离的目的，一定程度上避免了会导致线程阻塞的程序执行影响到反应式编程的程序执行效率。

Scheduler
先介绍 Scheduler 这个概念。在 Reactor 中，Scheduler 用来定义执行调度任务的抽象。可以简单理解为线程池，但其实际作用要更多。Scheduler 的实现包括：

Schedulers.elastic(): 调度器会动态创建工作线程，线程数无上界

Execturos.newCachedThreadPool()

当前线程，通过 Schedulers.immediate()方法来创建。

单一的可复用的线程，通过 Schedulers.single()方法来创建。

使用对并行操作优化的线程池，通过 Schedulers.parallel()方法来创建。其中的线程数量取决于 CPU 的核的数量。该调度器适用于计算密集型的流的处理。

使用支持任务调度的调度器，通过 Schedulers.timer()方法来创建。
从已有的 ExecutorService 对象中创建调度器，通过 Schedulers.fromExecutorService()方法来创建。
