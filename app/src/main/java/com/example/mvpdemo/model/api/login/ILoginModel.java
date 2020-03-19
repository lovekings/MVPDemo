package com.example.mvpdemo.model.api.login;

import com.example.mvpdemo.model.entry.BaseEntry;

import io.reactivex.Observable;


public interface ILoginModel {
    Observable<BaseEntry<String>> login(String name, String password);
}

