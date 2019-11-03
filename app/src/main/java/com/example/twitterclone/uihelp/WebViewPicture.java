package com.example.twitterclone.uihelp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewPicture extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}