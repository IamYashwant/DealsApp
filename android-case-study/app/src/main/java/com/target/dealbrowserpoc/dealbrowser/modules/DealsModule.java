package com.target.dealbrowserpoc.dealbrowser.modules;

import com.google.gson.Gson;
import com.target.dealbrowserpoc.dealbrowser.services.DealsService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yashwantsingh on 21/05/18.
 */

@Module
public class DealsModule {

    @Provides
    DealsService getDealsService(Retrofit retrofit) {
        return retrofit.create(DealsService.class);
    }

    @Provides
    public Retrofit createRetrofitService(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://target-deals.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
