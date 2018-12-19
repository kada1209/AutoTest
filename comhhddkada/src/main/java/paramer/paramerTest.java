package paramer;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * xml参数化测试
 * Created by Administrator on 2018/12/17.
 */

public class paramerTest {
    @Test
    @Parameters({"name","age"})
    public void paramerTest1(String name,int age){
        System.out.println("name is :"+ name +";  age is :"+age);
    }
}
