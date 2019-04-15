package httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2018/12/23.
 */
public class demo1 {
    @Test
    public void test1() throws IOException {
        String result;
        //定义一个发送get请求
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        //定义模拟一个客户端，用来执行get请求
        HttpClient httpClient = new DefaultHttpClient();
        ////定义一个 response，用来接收结果，类型是HttpResponse
        HttpResponse httpResponse = httpClient.execute(httpGet);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        Assert.assertTrue(result.contains("STATUS OK"));
    }
}
