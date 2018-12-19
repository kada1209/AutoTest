import org.testng.annotations.*;

/**
 * Created by Administrator on 2018/12/3.
 */
public class BasicAnnotation {
    @Test
    public void testCase1(){
        System.out.println("这是执行测试1");
    }
    @Test
    public void testCase2(){
        System.out.println("这是执行测试2");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是在测试方法之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("这是在测试方法之后运行的");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("测试套件，在类运行之前运行");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("测试套件，在类运行之后运行");
    }
}
