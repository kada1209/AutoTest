package testngStudy.multithreading;

import org.testng.annotations.Test;

import java.util.Date;

/**
 * xml实现多线程并发测试
 * Created by Administrator on 2018/12/19.
 */
public class ThreadOnXml {
    @Test(invocationCount = 20)
    public void  test1(){
        System.out.println(new Date().getTime()+"执行test1 : 线程id是："+Thread.currentThread().getId() );
    }
    @Test(invocationCount = 20)
    public void test2(){
        System.out.println(new Date().getTime()+"执行test1 : 线程id是："+Thread.currentThread().getId() );
    }
}
