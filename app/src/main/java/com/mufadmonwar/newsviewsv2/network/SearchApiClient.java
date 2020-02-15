package com.mufadmonwar.newsviewsv2.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchApiClient {


    private static Retrofit retrofit = null;

    public static ApiInterface getClient() {
        if (retrofit==null) {

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new ApiLogger())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpParams.NUMBERS_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
