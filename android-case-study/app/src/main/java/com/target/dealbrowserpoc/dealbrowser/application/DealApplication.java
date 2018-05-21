package com.target.dealbrowserpoc.dealbrowser.application;

import android.app.Application;
import android.content.Context;

import com.target.dealbrowserpoc.dealbrowser.component.DaggerDealApplicationComponent;
import com.target.dealbrowserpoc.dealbrowser.component.DealApplicationComponent;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class DealApplication extends Application {
    public static DealApplication get(Context context) {
        return (DealApplication) context.getApplicationContext();
    }

    private static DealApplicationComponent dealApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dealApplicationComponent = DaggerDealApplicationComponent.builder()
                .build();
    }

    public DealApplicationComponent getAppComponent() {
        return dealApplicationComponent;
    }
}
