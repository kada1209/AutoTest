import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2018/12/22.
 */
public class TestMethod{
    @Test
    public void test1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void test3(){
        Reporter.log("这是运行时的日志");
        throw new RuntimeException("运行时异常");

    }


}
