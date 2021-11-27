package com.db.structure.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {
    private final static String BASE_URL = "http://ec2-3-36-65-104.ap-northeast-2.compute.amazonaws.com:8080";
    public static Long accountId;

    private static Retrofit generateRetrofit(String password){

        if(password == null){
            password="";
        }

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        String finalPassword = password;

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("password", finalPassword).build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.retryOnConnectionFailure(false).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static MyApi generateMyApi(String password){
        Retrofit retrofit = generateRetrofit(password);

        return retrofit.create(MyApi.class);
    }
}
