package com.example.mydemo.login.view;

import android.content.Context;

public interface ILoginView {
    public Context getContext();
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
    public void onSetProgressBarVisibility(int visibility);
}
