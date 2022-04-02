package cn.ikyou.infrastructure.util;

import okhttp3.*;

import java.io.IOException;

/**
 * 开发人：石聪辉
 * 开发时间：2021/8/12
 * 说明：
 */
public class OkHttpUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(final String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }


    public static String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
