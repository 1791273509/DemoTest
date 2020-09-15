package testreference;


import lombok.extern.slf4j.Slf4j;

/**
 * @Author wenbaoxie
 * @Date 2020/9/15
 */
@Slf4j
public class Student_Fin {
//    该方法务必不要重写，不然会出问题，这里是为了清楚看到GC的过程，当GC发生时就会调用该函数
    @Override
    protected void finalize() throws Throwable {
        log.info("Student_Fin 被回收了");
        super.finalize();
    }
}
