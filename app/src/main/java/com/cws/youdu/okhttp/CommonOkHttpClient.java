package com.cws.youdu.okhttp;


import com.cws.youdu.okhttp.https.HttpsUtils;
import com.cws.youdu.okhttp.listener.CallbackListener;
import com.cws.youdu.okhttp.listener.DisposeDownloadListener;
import com.cws.youdu.okhttp.request.CommonRequest;
import com.cws.youdu.okhttp.request.RequestParams;
import com.cws.youdu.okhttp.response.CommonFileCallback;
import com.cws.youdu.okhttp.response.CommonJsonCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 * Created by cws on 2017/3/13.
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        /**
         *  为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "Imooc-Mobile") // 标明发送本次请求的客户端
                        .build();
                return chain.proceed(request);
            }
        });
//        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
        /**
         * trust all the https point
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpClientBuilder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     *
     * @param url
     * @param params
     * @param listener
     * @return
     */
    public static Call get(String url, RequestParams params, CallbackListener listener) {
        Request getRequest = CommonRequest.createGetRequest(url, params);
        Call call = mOkHttpClient.newCall(getRequest);
        call.enqueue(new CommonJsonCallback(listener));
        return call;
    }

    /**
     *
     * @param url
     * @param params
     * @param listener
     * @return
     */
    public static Call post(String url, RequestParams params, CallbackListener listener) {
        Request postRequest = CommonRequest.createPostRequest(url, params);
        Call call = mOkHttpClient.newCall(postRequest);
        call.enqueue(new CommonJsonCallback(listener));
        return call;
    }

    /**
     * 下载文件
     * @param url
     * @param params
     * @param listener
     * @param filePath
     * @return
     */
    public static Call downloadFile(String url, RequestParams params, DisposeDownloadListener listener, String filePath) {
        Request multiPostRequest = CommonRequest.createMultiPostRequest(url, params);
        Call call = mOkHttpClient.newCall(multiPostRequest);
        call.enqueue(new CommonFileCallback(listener, filePath));
        return call;
    }
}
