import org.testng.annotations.Test;

/**
 * 超时测试
 * Created by Administrator on 2018/12/19.
 */
public class TimeOutTest {
    @Test(timeOut = 2000)
    public void testFaile() throws InterruptedException {
        Thread.sleep(3000);
    }
}
