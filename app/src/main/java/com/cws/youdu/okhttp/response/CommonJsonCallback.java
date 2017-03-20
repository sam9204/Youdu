package com.cws.youdu.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.cws.youdu.okhttp.exception.OkHttpException;
import com.cws.youdu.okhttp.listener.CallbackListener;
import com.cws.youdu.utils.TLog;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * 专门处理JSON的回调
 * (考虑使用泛型)
 * Created by cws on 2017/3/13.
 */

public class CommonJsonCallback implements Callback {
    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private CallbackListener mListener;

    public CommonJsonCallback(CallbackListener listener) {
        this.mListener = listener;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                TLog.i(ioexception.getMessage());
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                TLog.i(result);
                handleResponse(result);
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

//    private void HandleResponse(String result) {
//        if (TextUtils.isEmpty(result)) {
//            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
//            return;
//        }
//        mListener.onSuccess(result);
//    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        mListener.onSuccess(responseObj);

        try {
            //TODO
//            JSONObject result = new JSONObject(responseObj.toString());
//                Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
//                if (obj != null) {
//                    mListener.onSuccess(obj);
//                } else {
//                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
//                }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
