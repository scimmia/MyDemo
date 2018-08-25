package com.scimmia.mydemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;

/**
 * Created by lj on 2018/8/18.
 */

public class LocationJobservice extends JobService{
    public LocationService locationService;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        try {
            locationService = ((LocationApplication)getApplicationContext()).locationService;
            locationService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
//        try {
//            locationService.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return false;
    }
}
