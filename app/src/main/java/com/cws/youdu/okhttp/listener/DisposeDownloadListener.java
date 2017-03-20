package com.cws.youdu.okhttp.listener;

/**
 * 监听下载进度
 * Created by cws on 2017/3/13.
 */
public interface DisposeDownloadListener extends CallbackListener{
    void onProgress(int progrss);
}
