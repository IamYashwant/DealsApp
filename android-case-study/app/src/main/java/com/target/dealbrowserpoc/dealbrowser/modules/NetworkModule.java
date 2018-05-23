package com.target.dealbrowserpoc.dealbrowser.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yashwantsingh on 21/05/18.
 */

@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides
    public File getFile(Context context) {
        File cacheFile = new File(context.getCacheDir(), "deals_cache");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    public Cache getCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 10 * 1000); //10MB
    }

    @Provides
    public OkHttpClient getOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
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
