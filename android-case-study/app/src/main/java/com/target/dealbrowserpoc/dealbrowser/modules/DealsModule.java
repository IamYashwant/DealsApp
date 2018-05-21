package com.target.dealbrowserpoc.dealbrowser.modules;

import com.target.dealbrowserpoc.dealbrowser.services.DealsService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yashwantsingh on 21/05/18.
 */

@Module
public class DealsModule {
    @Provides
    DealsService getDealsService(Retrofit retrofit){
        return retrofit.create(DealsService.class);
    }
}
