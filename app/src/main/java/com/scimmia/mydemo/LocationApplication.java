package com.scimmia.mydemo;


import android.app.Application;
import android.app.Service;
import android.os.AsyncTask;
import android.os.Vibrator;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.scimmia.mydemo.utils.DebugLog;
import com.scimmia.mydemo.utils.http.HttpTask;
import com.xdandroid.hellodaemon.DaemonEnv;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import okhttp3.FormBody;
import okhttp3.MediaType;


/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 *
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * 
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
	public LocationService locationService;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
//        locationService = new LocationService(getApplicationContext());
//        locationService.registerListener(mListener);
//        locationService.start();
//        try {
//            PeriodicWorkRequest periodicWorkRequest =
//                    new PeriodicWorkRequest.Builder(LocationWork.class,2, TimeUnit.MINUTES)
//                    .build();
//            WorkManager.getInstance().enqueue(periodicWorkRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        DaemonEnv.initialize(this, TraceServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
//        TraceServiceImpl.sShouldStopService = false;
//        DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        try {
            locationService.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LocationListener mListener = new LocationListener() ;

}
