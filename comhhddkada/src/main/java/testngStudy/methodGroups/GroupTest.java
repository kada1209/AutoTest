package testngStudy.methodGroups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2018/12/9.
 */
public class GroupTest {
    @Test(groups = "server")
    public void testOnServer1(){
        System.out.println("这是服务端测试case1");
    }
    @Test(groups = "server")
    public void testOnServer2(){
        System.out.println("这是再服务端测试case2");
    }
    @Test(groups = "client")
    public void testOnClient1() {
        System.out.println("这是在客户端测试case1");
    }
    @Test(groups = "client")
    public void testOnClient2(){
        System.out.println("这是在客户端测试case2");
        }

    @BeforeGroups("server")
     public  void beforeGroupOnServer(){
        System.out.println("这是服务端组运行测试之前");
    }
    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("这是服务端组运行测试之后");
    }
    @BeforeGroups("client")
     public void beforeGroupOnClient(){
        System.out.println("这是在客户端组运行测试之前");
    }
    @AfterGroups("client")
    public void afterGroupOnClient(){
        System.out.println("这是在客户端运行测试之后");
    }





}
