package com.example.mvpdemo.model.http;

import com.example.mvpdemo.config.BuildConfig;
import com.example.mvpdemo.model.api.ApiSource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils{
    private static RetrofitUtils retrofitUtils;
    private static  final String BASE_URL= BuildConfig.API_URL;
    private  static ApiSource apiSource;
    //单例模式，懒汉模式

    static OkHttpClient okhttpClient= new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2,TimeUnit.MINUTES)
            .writeTimeout(2,TimeUnit.MINUTES)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    return chain.proceed(request);
                }
            })
            .addInterceptor(new HttpLoggingInterceptor())
            .build();
     static  Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build();

    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public static ApiSource getApiSource() {
        return retrofit.create(ApiSource.class);
    }
}
