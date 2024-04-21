package com.example.appbanhang.Retrofit;

import com.google.gson.Gson;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientNoti {
    private static Retrofit instance;

//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

    public static Retrofit getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl("https://fcm.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}
