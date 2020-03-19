package com.example.mvpdemo.model.api;

import android.database.Observable;

import com.example.mvpdemo.model.entry.BaseEntry;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSource {
    /**
     * 登录
     *
     * @param username：用户名
     * @param password：密码
     * @return
     * @FormUrlEncoded：表单请求。 请求方法类
     * GET、POST、PUT、DELETE、PATCH、HEAD、OPTIONS、HTTP
     * 标记类
     * FormUrlEncoded、Multipart、Streaming
     * 参数类
     * Headers、Header、Body、Field、FieldMap、Part、PartMap、Query、QueryMap、Path、URL
     */
    @FormUrlEncoded
    @POST("user/login")
    io.reactivex.Observable<BaseEntry<String>> login(@Field("username") String username, @Field("password") String password);
}
