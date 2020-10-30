package testAnno;

import testgroovy.Test;

/**
 * @Author wenbaoxie
 * @Date 2020/10/30
 */
@MyAnno(value = "dd")
public class TestMyAnno {
    public static void main(String[] args) {
        Class<? extends TestMyAnno> aClass = new TestMyAnno().getClass();
        if(aClass.isAnnotationPresent(MyAnno.class)) {
            MyAnno annotation = aClass.getAnnotation(MyAnno.class);
            System.out.println(annotation.value());
        }
        MyAnno annotation = aClass.getAnnotation(MyAnno.class);
        System.out.println(annotation.value());
    }

}
