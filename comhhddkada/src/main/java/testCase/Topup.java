package testCase;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * Created by Administrator on 2019/1/9.
 */
public class Topup {
    private String url;
    //这个工具类就是为了读取properties这样的配置文件的
    private ResourceBundle resourceBundle;
    //存储cookie值
    private CookieStore cookieStore;
    @BeforeTest
    public void beforeTest() {
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = resourceBundle.getString("test.url");
        System.out.println(url);
    }

    /**
     * c充值接口
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test(invocationCount = 1)
    public void topupTest() throws URISyntaxException, IOException {
        String result;
        String uri = resourceBundle.getString("Topup.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url+uri);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("deviceType","4");
        BasicNameValuePair param2 = new BasicNameValuePair("walletTopupInfoId","1");
        BasicNameValuePair param3 = new BasicNameValuePair("channel","1");
        BasicNameValuePair param4 = new BasicNameValuePair("deviceId","1faf7851-05c8-3bfe-ab4d-63b201f629b1");
        list.add(param1);
        list.add(param2);
        list.add(param3);
        list.add(param4);
        uriBuilder.setParameters(list);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(new BasicHeader("Cookie", "_HHDD_=\"{\\\"userId\\\":60006992,\\\"nick\\\":null,\\\"token\\\":\\\"c7afbd67aef77ee1cada32de8ec45075\\\",\\\"extTime\\\":1578569411750,\\\"login\\\":true}\""));
        httpGet.setHeader("Authorization","EREjGUoaGFxYcFBqBjUmOg9ACWMfeyMjWwFTZAN1EnB4NnlAb0svGx4ZL34pA1hzZiw=");
        httpGet.setHeader("RDI","DT=DUK-TL30;SV=8.0.0;AV=4.0.10;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //遍历请求头信息
        Header[] list1 = httpGet.getAllHeaders();
        for (Header h:list1){
            System.out.println(h);
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //获取返回实体
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(entity);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        //获取响应结果
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

//        //将获得去的结果转换为json
//        JSONObject resultJson = new JSONObject(result);
//        System.out.println(resultJson);

        Assert.assertEquals(statusCode,200);

    }

    /**
     * 订单支付接口
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test(invocationCount = 10000)
    public void OrderPreTest() throws URISyntaxException, IOException {
        String result;
        String uri = resourceBundle.getString("preOrder.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url+uri);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("orderId","0");
        BasicNameValuePair param2 = new BasicNameValuePair("collectId","60627");
        BasicNameValuePair param3 = new BasicNameValuePair("type","1");
        BasicNameValuePair param4 = new BasicNameValuePair("deviceId","b40b0052-f4c5-34bb-ab39-faad1265d74f");
        list.add(param1);
        list.add(param2);
        list.add(param3);
        list.add(param4);
        uriBuilder.setParameters(list);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(new BasicHeader("Cookie", "_HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        httpGet.setHeader("Authorization","DFAVFVgzKyoTXF5JBQoYOgF6IjMMUnM0SQ4FAiZ0a2g0c1Vwc0szKzJcY2ZQAn0kHTE=");
        httpGet.setHeader("RDI","DT=MIX 2S;SV=9;AV=4.0.10;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //遍历请求头信息
        Header[] list1 = httpGet.getAllHeaders();
        for (Header h:list1){
            System.out.println(h);
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //获取返回实体
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(entity);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        //获取响应结果
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

//        //将获得去的结果转换为json
//        JSONObject resultJson = new JSONObject(result);
//        System.out.println(resultJson);

        Assert.assertEquals(statusCode,200);

    }

    /**
     * 新支付接口
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test(invocationCount = 1)
    public void OrderContinueTest() throws URISyntaxException, IOException {
        String result;
        String uri = resourceBundle.getString("continuepay.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url+uri);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("orderId","58604");
        BasicNameValuePair param2 = new BasicNameValuePair("channel","99");
        BasicNameValuePair param3 = new BasicNameValuePair("type","1");
        BasicNameValuePair param4 = new BasicNameValuePair("version","1");
        list.add(param1);
        list.add(param2);
        list.add(param3);
        list.add(param4);
        uriBuilder.setParameters(list);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(new BasicHeader("Cookie", "_HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        httpGet.setHeader("Authorization","IQImLWYFNz4vfXtZPBNeEih0GTYRVVIWUTt1EBlXWE8uZlwTeks6SDtJeUFjIUIXRxw=");
        httpGet.setHeader("RDI","DT=MIX 2S;SV=9;AV=4.0.10;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //遍历请求头信息
        Header[] list1 = httpGet.getAllHeaders();
        for (Header h:list1){
            System.out.println(h);
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //获取返回实体
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(entity);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        //获取响应结果
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

//        //将获得去的结果转换为json
//        JSONObject resultJson = new JSONObject(result);
//        System.out.println(resultJson);

        Assert.assertEquals(statusCode,200);

    }
}
