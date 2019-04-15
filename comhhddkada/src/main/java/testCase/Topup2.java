package testCase;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HttpClientUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2019/1/10.
 */
public class Topup2 {
    private String url;
    //这个工具类就是为了读取properties这样的配置文件的
    private ResourceBundle resourceBundle;
    @BeforeTest
    public void beforeTest() {
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = resourceBundle.getString("test.url");
        System.out.println(url);
    }

    /**
     * 充值接口
     */
    @Test(invocationCount = 1,dataProvider = "methodDataTest")
    public void topupTest(String cookie,String authorization){
        String uri = resourceBundle.getString("Topup.uri");
        String nowUrl = url+uri;
        //设置参数
        Map<String,String> map = new HashMap<>();
        map.put("deviceType","1");
        map.put("walletTopupInfoId","4");
        map.put("channel","1");
        map.put("deviceId","1faf7851-05c8-3bfe-ab4d-63b201f629b1");
        //设置请求头
        Header[] headers = new Header[4];
        headers[0]=(new BasicHeader("Cookie",cookie ));
        headers[1]=(new BasicHeader("Authorization",authorization) );
        headers[2]=(new BasicHeader("RDI","DT=DUK-TL30;SV=8.0.0;AV=4.0.10;CH=debugpackage"));
        headers[3]=(new BasicHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)"));
        String result= HttpClientUtils.doGet(nowUrl,map,headers);
        System.out.println(result);
//        String outTradeNo = HttpClientUtils.getJsonValue(result,"data");
//        System.out.println(outTradeNo);
    }

    /**
     * 充值成功回调接口
     */
    @Test(dependsOnMethods = "topupTest")
    public void queryOrder(){
        String uri = resourceBundle.getString("queryOrder.uri");
        String nowUrl = url+uri;
        //设置参数
        Map<String,String> map = new HashMap<>();
        map.put("orderNo","WTO20190110071244849657820");
        //设置请求头
        Header[] headers = new Header[4];
        headers[0]=(new BasicHeader("Cookie", " _HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        headers[1]=(new BasicHeader("Authorization","AigzBlExGQgkGANgehY4bSRSWhQSbk4NTgdEGQljTiI+YUYNRksGViFOaSx1FVIKSz8=") );
        headers[2]=(new BasicHeader("RDI","DT=DUK-TL30;SV=8.0.0;AV=4.0.10;CH=debugpackage"));
        headers[3]=(new BasicHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)"));
        String result= HttpClientUtils.doGet(nowUrl,map,headers);
        System.out.println(result);

    }

    /**
     * 预订单接口
     */
    @Test(invocationCount = 10,dependsOnMethods = "topupTest")
    public void OrderPreTest(){
        String uri = resourceBundle.getString("preorider.uri");
        String nowUrl = url+uri;
        //设置参数
        Map<String,String> map = new HashMap<>();
        map.put("orderId","0");
        map.put("collectId","60896");
        map.put("type","1");
        map.put("deviceId","1faf7851-05c8-3bfe-ab4d-63b201f629b1");
        //设置请求头
        Header[] headers = new Header[4];
        headers[0]=(new BasicHeader("Cookie", " _HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        headers[1]=(new BasicHeader("Authorization","AigzBlExGQgkGANgehY4bSRSWhQSbk4NTgdEGQljTiI+YUYNRksGViFOaSx1FVIKSz8=") );
        headers[2]=(new BasicHeader("RDI","DT=DUK-TL30;SV=8.0.0;AV=4.0.10;CH=debugpackage"));
        headers[3]=(new BasicHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)"));
        String result= HttpClientUtils.doGet(nowUrl,map,headers);
        System.out.println(result);
    }

    /**
     * 真正支付接口
     */
    @Test(invocationCount = 10,dependsOnMethods = "OrderPreTest")
    public void OrderContinueTest(){
        String uri = resourceBundle.getString("orderpay.uri");
        String nowUrl = url+uri;
        //设置参数
        Map<String,String> map = new HashMap<>();
        map.put("orderId","58604");
        map.put("channel","99");
        map.put("type","1");
        map.put("version","1");
        //设置请求头
        Header[] headers = new Header[4];
        headers[0]=(new BasicHeader("Cookie", " _HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        headers[1]=(new BasicHeader("Authorization","AigzBlExGQgkGANgehY4bSRSWhQSbk4NTgdEGQljTiI+YUYNRksGViFOaSx1FVIKSz8=") );
        headers[2]=(new BasicHeader("RDI","DT=DUK-TL30;SV=8.0.0;AV=4.0.10;CH=debugpackage"));
        headers[3]=(new BasicHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)"));
        String result= HttpClientUtils.doGet(nowUrl,map,headers);
        System.out.println(result);
    }


    /**
     * 数据生成
     * @param method
     */
    @DataProvider(name = "methodDataTest")
    public Object[][] methodData(Method method){
        Object[][] result = null;
        if (method.getName().equals("topupTest")){
            result = new Object[][]{
                    {("_HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""),"AigzBlExGQgkGANgehY4bSRSWhQSbk4NTgdEGQljTiI+YUYNRksGViFOaSx1FVIKSz8="}

            };
        }
        return result;
    }
}


