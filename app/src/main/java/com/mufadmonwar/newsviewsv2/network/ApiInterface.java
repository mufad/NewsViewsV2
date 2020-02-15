package com.mufadmonwar.newsviewsv2.network;


import com.mufadmonwar.newsviewsv2.model.headlines.TopNewsResponse;
import com.mufadmonwar.newsviewsv2.model.news_source.NewsSourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(HttpParams.API_SOURCES)
    Call<NewsSourcesResponse> getNewsSources(@Query(HttpParams.PARAM_LANGUAGE) String language,
                                             @Query(HttpParams.PARAM_COUNTRY) String country,
                                             @Query(HttpParams.PARAM_API_KEY) String apiKey);


    @GET(HttpParams.API_HEADLINES)
    Call<TopNewsResponse>getTopNews(@Query(HttpParams.PARAM_SOURCES) String source,
                                    @Query(HttpParams.PARAM_API_KEY) String apiKey);
}
