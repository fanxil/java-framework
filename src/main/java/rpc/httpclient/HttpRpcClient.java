package rpc.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 基于http远程调用实现
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class HttpRpcClient {


    /**
     * http一次请求一次响应
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet("https://www.baidu.com/?tn=54093922_hao_pg");


        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();

        String responseStr = EntityUtils.toString(entity);

        System.out.println(responseStr);
    }
}
