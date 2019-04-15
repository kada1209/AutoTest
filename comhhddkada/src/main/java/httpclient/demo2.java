package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.http.cookie.Cookie;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/12/25.
 */
public class demo2 {
    private String url;
    //这个工具类就是为了读取properties这样的配置文件的
    private ResourceBundle resourceBundle;
    //存储cookie值
    private CookieStore cookieStore;
    @BeforeTest
    public void beforeTest(){
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = resourceBundle.getString("test.url");
        System.out.println(url);
    }

    /**
     * 获取cookie
     * @throws IOException
     */
    @Test
    private void testGetCookies() throws IOException {
        String result;
        String uri = resourceBundle.getString("getUserRedPacketsInfo.uri");
        HttpGet httpGet = new HttpGet(this.url+uri);
        System.out.println(this.url+uri);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //设置请求头
        httpGet.setHeader("Content-Type","application/json");
        httpGet.setHeader("Authorization","ACY9K0IiFy00YVRRAhIVJxYDDX8CSiZ9WxA6fwBgNntKQ3V3c0szLBJsHXUNFltWHj0=");
        httpGet.setHeader("Cookie","_HHDD_=\"{\\\"userId\\\":60007547,\\\"nick\\\":null,\\\"token\\\":\\\"3e6c0faa6c3f2d1d08d87767e223fbf3\\\",\\\"extTime\\\":1577275551687,\\\"login\\\":true}\"");
        httpGet.setHeader("RDI","DT=HUAWEI NXT-AL10;SV=8.0.0;AV=3.8.1;CH=debugpackage");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode==200){
            result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            JSONObject resultJson = new JSONObject(result);
            System.out.println(resultJson);
        }
        Assert.assertEquals(statusCode,200);

        //cookieStore = httpClient.getCookieStore();
//        List<Cookie> list = cookieStore.getCookies();
//        for (Cookie cookie:list){
//            System.out.println(cookie);
//            System.out.println(1);
//        }

    }

    /**
     * 这个测试会用到上一个方法得到的cookie
     * get 请求
     * @throws IOException
     */
    @Test(dependsOnMethods = "testGetCookies")
    public void testGet() throws IOException {
        String result;
        //定义一个get请求地址
        HttpGet httpGet = new HttpGet(url);
        //定义一个客户端
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //使用上一个方法获得的cookie
        httpClient.setCookieStore(cookieStore);
        //定义一个response用来接收信息
        HttpResponse httpResponse = httpClient.execute(httpGet);

        //获取响应状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode==200){
            result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(result);
            Assert.assertTrue(result.contains("STATUS OK"));
        }
    }

    /**
     *
     */
    @Test(dependsOnMethods = "testGetCookies")
    public void testPost() throws IOException {
        String result;
        //定义一个post请求
        HttpPost httpPost = new HttpPost(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //代入Cookie值
        httpClient.setCookieStore(cookieStore);
        //添加参数信息
        JSONObject param = new JSONObject();
        param.put("name","wxq");
        param.put("age","24");
        //携带请求头
        httpPost.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode==200){
            //获取响应结果
            result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            //将获得去的结果转换为json
            JSONObject resultJson = new JSONObject(result);
            //判断返回的结果的值


        }

    }
}
