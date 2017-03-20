package com.cws.youdu.okhttp;

import com.cws.youdu.okhttp.listener.CallbackListener;
import com.cws.youdu.okhttp.request.RequestParams;

/**
 * Created by cws on 2017/3/20.
 */

public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, CallbackListener listener) {
        CommonOkHttpClient.post(url, params, listener);
    }

    public static void requestRecommandData (CallbackListener listener) {
        postRequest(Api.HOME_RECOMMAND, null, listener);
    }

}
