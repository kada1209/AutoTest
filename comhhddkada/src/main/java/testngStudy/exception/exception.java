package testngStudy.exception;

import org.testng.annotations.Test;

/**
 * Created by Administrator on 2018/12/12.
 */
public class exception {

    /**
     * 运行一个异常会失败的测试
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个运行会失败的测试");
    }

    /**
     * 运行一个异常会运行成功的测试
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSucess(){
        System.out.println("这是一个异常但是会运行成功的测试");
        throw new RuntimeException();
    }

}
