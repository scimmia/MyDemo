package com.scimmia.mydemo.utils.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.scimmia.mydemo.utils.DebugLog;

import org.apache.commons.lang3.StringUtils;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by ASUS on 2014/12/4.
 */
public class HttpTask extends AsyncTask<Void,Void,String> {

    String mUrl;
    String mTag;
    RequestBody mFormBody;


    private static OkHttpClient okHttpClient;
    private static OkHttpClient getOkHttpClient(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }


    public HttpTask(String url, String tag, RequestBody formBody) {
        this.mUrl = url;
        this.mTag = tag;
        this.mFormBody = formBody;

//        mUrl = "http://ytbus.jiaodong.cn:4990/BusPosition.asmx/GetBusLineStatusEncry";
        DebugLog.e(this.mUrl);
    }


    @Override
    protected String doInBackground(Void... params) {
        Request request;
        if (mFormBody == null){
            request = new Request.Builder().tag(mTag)
                    .url(mUrl)
                    .build();
        }else {
            request = new Request.Builder().tag(mTag)
                    .url(mUrl)
                    .post(mFormBody)
                    .build();
        }
        if (request != null){
            OkHttpClient okHttpClient = getOkHttpClient();
            try {
                Response response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String temp = response.body().string();
                    DebugLog.e(temp);
                    return temp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        DebugLog.e("onCancelled");
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
            if (StringUtils.equalsIgnoreCase((String)call.request().tag(),mTag))
                call.cancel();
        }
        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {
            if (StringUtils.equalsIgnoreCase((String)call.request().tag(),mTag))
                call.cancel();
        }
    }
}