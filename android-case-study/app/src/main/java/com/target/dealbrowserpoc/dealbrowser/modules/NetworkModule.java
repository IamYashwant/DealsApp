package com.target.dealbrowserpoc.dealbrowser.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yashwantsingh on 21/05/18.
 */

@Module
public class NetworkModule {
    @Provides
    public OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit createRetrofitService(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl("http://target-deals.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
