package com.cws.youdu.okhttp.listener;

/**
 * 业务逻辑层真正处理的地方，包括java层异常和业务层异常
 * Created by cws on 2017/3/13.
 */

public interface CallbackListener {
    /**
     * 请求成功回调事件处理
     */
    void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(Object reasonObj);
}
