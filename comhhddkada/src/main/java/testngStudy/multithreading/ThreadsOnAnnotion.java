package testngStudy.multithreading;

import org.testng.annotations.Test;

import java.util.Date;

/**
 * 注解方式实现多线程
 * Created by Administrator on 2018/12/18.
 */

public class ThreadsOnAnnotion {
    @Test(invocationCount = 100,threadPoolSize = 10)
    public void test(){

            System.out.println(new Date().getTime()+"test    "+Thread.currentThread().getId());



    }
//    @Test(invocationCount = 10,threadPoolSize = 3)
//    public void test1(){
//        System.out.println(new Date().getTime()+"test1     "+Thread.currentThread().getId());
//    }

}
