package com.example.retrofitdemo.retrofit;

public class ApiUtility {

    public static ApiInterface getApi(){

        return RetrofitClient.getInstance().create(ApiInterface.class);

    }
}
