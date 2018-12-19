package suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Administrator on 2018/12/5.
 */
public class suiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("套件测试开始");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("测试套件结束");
    }
}
