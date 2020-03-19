package com.example.mvpdemo.model.api.login;

import com.example.mvpdemo.model.entry.BaseEntry;
import com.example.mvpdemo.model.http.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginModel {
    private RetrofitUtils retrofitUtils=RetrofitUtils.getInstance();
    @Override
    public Observable<BaseEntry<String>> login(String name, String password) {
        return retrofitUtils.getApiSource().login(name,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
