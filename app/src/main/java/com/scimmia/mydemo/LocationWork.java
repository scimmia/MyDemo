package com.scimmia.mydemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.scimmia.mydemo.utils.DebugLog;

import androidx.work.Worker;

/**
 * Created by lj on 2018/8/17.
 */

public class LocationWork extends Worker {
    public LocationService locationService;
    static LocationListener listener = new LocationListener();
    @NonNull
    @Override
    public Result doWork() {
        DebugLog.e("doWork");
//        try {
//            Context applicationContext = getApplicationContext();
//
//            locationService = new LocationService(getApplicationContext());
//            locationService.registerListener(listener);
//            locationService.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            locationService = ((LocationApplication)getApplicationContext()).locationService;
            locationService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.RETRY;
    }

    @Override
    public void onStopped(boolean cancelled) {
        DebugLog.e("doWork--onStopped");
        super.onStopped(cancelled);
//        try {
//            locationService.unregisterListener(listener);
//            locationService.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
