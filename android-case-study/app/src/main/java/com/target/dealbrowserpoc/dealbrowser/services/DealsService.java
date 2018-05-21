package com.target.dealbrowserpoc.dealbrowser.services;

import com.target.dealbrowserpoc.dealbrowser.entities.deals.RealDeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public interface DealsService {
    @GET("api/deals")
    Call<RealDeal> getDeals();
}
