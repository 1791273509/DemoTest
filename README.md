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
[WebClient使用](https://blog.csdn.net/iteye_13139/article/details/82726588)