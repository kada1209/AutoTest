package testngStudy.classGroups;

import org.testng.annotations.Test;

/**
 * Created by Administrator on 2018/12/12.
 */
@Test(groups = "teacher")
public class groupClassOnTeacher {
    public void test(){
        System.out.println("运行在类分组teacher 方法1111111111");
    }
}
