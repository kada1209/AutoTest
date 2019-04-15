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
import org.apache.http.message.BasicHeader;
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

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * 4.0红包系统测试
 * Created by Administrator on 2018/12/25.
 */
public class StudyToRed {
    private String url;
    //这个工具类就是为了读取properties这样的配置文件的
    private ResourceBundle resourceBundle;
    //存储cookie值
    private CookieStore cookieStore;

//    @BeforeTest
//    public void beforeTest() {
//        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
//        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
//        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
//        url = resourceBundle.getString("StudyToRed.uri");
//
//    }

    /**
     * 接口
     */
    @Test
    public void study() throws URISyntaxException, IOException {
//        String uri = resourceBundle.getString("awardUserDeviceId.uri");
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = resourceBundle.getString("StudyToRed.uri");

        URIBuilder uriBuilder = new URIBuilder(this.url);

        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param = new BasicNameValuePair("activityKey","795091313");
        list.add(param);
        uriBuilder.addParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization","HhspKFgbBlYvQHtULBUIGANlXzIVSCQgfypaaQFtQjhXTWtFcUsxHgxiADZ5G1p+TiM=");
        httpGet.setHeader("RDI","DT=MIX 2;SV=8.0.0;AV=4.0.20;CH=debugpackage");
        httpGet.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 8.0.0; MIX 2 MIUI/8.4.20)");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Accept-Encoding", "gzip");
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
     * 接口
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void sign() throws IOException, URISyntaxException {
        String result;
        //String uri = resourceBundle.getString("refrshToken.uri");
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = resourceBundle.getString("Sign.uri");
        URIBuilder uriBuilder = new URIBuilder(this.url);
        //设置参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("activityKey", "795091313");
        BasicNameValuePair param2 = new BasicNameValuePair("presentType", "3");

        list.add(param1);
        list.add(param2);
        uriBuilder.setParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //设置请求头
        //httpGet.setHeader("Content-Type","text/html;charset=UTF-8");
        httpGet.setHeaders(new BasicHeader[]{new BasicHeader("Cookie", "_HHDD_=\"{\\\"userId\\\":60010848,\\\"nick\\\":null,\\\"token\\\":\\\"2a8517a459c5d3defb0d048dc02a7d5c\\\",\\\"extTime\\\":1583292246595,\\\"login\\\":false}\"")});
//        #httpGet.setHeader(new BasicHeader("Cookie", "_HHDD_=\"{\\\"userId\\\":60009357,\\\"nick\\\":null,\\\"token\\\":\\\"30fd098ecd692e87f1dc9d3ff52efae5\\\",\\\"extTime\\\":1578623190629,\\\"login\\\":true}\""));
        httpGet.setHeader("Authorization","HhspKFgbBlYvQHtULBUIGANlXzIVSCQgfypaaQFtQjhXTWtFcUsxHgxiADZ5G1p+TiM=");
        httpGet.setHeader("RDI","DT=MIX 2;SV=8.0.0;AV=4.0.20;CH=debugpackage");
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