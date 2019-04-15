package testCase;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
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
 * 4.0红包系统测试
 * Created by Administrator on 2018/12/25.
 */
public class RedPacket {
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
     * /security/awardYUserDeviceId.json 接口
     */
    @Test
    public void awardUserDeviceId() throws URISyntaxException, IOException {
        String uri = resourceBundle.getString("awardUserDeviceId.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url+uri);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param = new BasicNameValuePair("deviceId","04067553-3eea-3be5-8799-067ab4f9e502");
        list.add(param);
        uriBuilder.addParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization","DxIvJWspBQY5Z3N3CCc7GwlaCBYSVhdICU07XBJZVGw4UU9+YEsgJSh+b2JvL0loKjI=");
        httpGet.setHeader("RDI","DT=MIX 2;SV=8.0.0;AV=3.8.1;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int codeStatus = httpResponse.getStatusLine().getStatusCode();
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(entity.getContentEncoding());
        String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);
        Assert.assertEquals(codeStatus,200);
    }

    /**
     * /security/refreshToken.json 接口
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test(dependsOnMethods = "awardUserDeviceId")
    public void refreshToken() throws IOException, URISyntaxException {
        String result;
        String uri = resourceBundle.getString("refrshToken.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url+uri);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("deviceld", "4419efca-b61d-3a8d-a06a-f261d5106480");
        BasicNameValuePair param2 = new BasicNameValuePair("clientSecretText", "a6603d584a229591862e62c31a2e5036;4419efca-b61d-3a8d-a06a-f261d5106480");
        list.add(param1);
        list.add(param2);
        uriBuilder.setParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //设置请求头
        //httpGet.setHeader("Content-Type","text/html;charset=UTF-8");
        httpGet.setHeader("Authorization","HhspKFgbBlYvQHtULBUIGANlXzIVSCQgfypaaQFtQjhXTWtFcUsxHgxiADZ5G1p+TiM=");
        httpGet.setHeader("RDI","DT=MIX 2;SV=8.0.0;AV=3.8.1;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Accept-Encoding", "gzip");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        //获取Cookie信息
        cookieStore = httpClient.getCookieStore();
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

