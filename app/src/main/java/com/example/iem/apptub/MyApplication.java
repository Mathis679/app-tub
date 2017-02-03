package com.example.iem.apptub;

import android.app.Application;

import com.example.iem.apptub.database.PointsData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by iem on 03/02/2017.
 */

public class MyApplication extends Application {
    @Override public void onCreate () {


        super.onCreate(); // This instantiates DBFlow FlowManager . init ( new FlowConfig . Builder ( this ). build ()); // add for verbose logging // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V); } }
        FlowManager.init(new FlowConfig.Builder(this).build());



    }
    }
