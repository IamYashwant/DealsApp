package com.target.dealbrowserpoc.dealbrowser.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yashwantsingh on 23/05/18.
 */

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }
}
