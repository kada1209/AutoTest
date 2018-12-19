package paramer;

import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * dataProvider参数花测试
 * Created by Administrator on 2018/12/18.
 */
public class DataProvider {
    @Test(dataProvider = "simpleTest")
    public void dataProviderTest(String name,int age){
        System.out.println("简单的dataProvider参数化用法 name is"+name+";age is "+age);
    }
    @org.testng.annotations.DataProvider(name = "simpleTest",parallel = true)
    public Object[][] data(){
        Object[][] o = new Object[][]{
                {"王新起",25},
                {"习近平",52}
        };
        return o;
    }


    //来一个复杂点的用法
    @Test(dataProvider = "methodDataTest")
    public void test1(String name ,int age){
        System.out.println("稍微复杂点的用法test111 name is "+name +"; age is "+age);
    }
    @Test(dataProvider = "methodDataTest")
    public void test2(String name,int age){
        System.out.println("稍微复杂点的用法test222 name is "+name +"; age is "+age);
    }
    @org.testng.annotations.DataProvider(name = "methodDataTest")
    public Object[][] methodData(Method method){
        Object[][] result = null;
        if (method.getName().equals("test1")){
            result= new Object[][]{
                    {"赵四",88}
            };
        }else if (method.getName().equals("test2")){
            result = new Object[][]{
                    {"王五",99}
            };
        }
        return result;
    }
}
