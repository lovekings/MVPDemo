package com.example.mvpdemo.view;

public interface ILoginView {
    public void  LoginMessageSend(boolean flag);//发送验证码
    public  void Login_Login_btn(boolean flag);//登录
    public  void Login_Message_btn(boolean flag);//短信验证登录按钮
    public void Login_other_selector(int flag);//第三方登录
    public  boolean  Login_forget_password();//忘记密码判断
}
