package com.example.mvpdemo.view.base;



import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.mvpdemo.utils.ActivityUtils;



public abstract class BaseActivity extends FragmentActivity {
    private ActivityUtils activityUtils=ActivityUtils.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        activityUtils.addActivity(this);
        InitView();
        InitListener();

    }
    //软键盘隐藏
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
    public abstract void InitView();
    public  abstract  void  InitListener();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityUtils.finishActivity(this);
    }
}
