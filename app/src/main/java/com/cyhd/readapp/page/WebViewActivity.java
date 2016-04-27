package com.cyhd.readapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cyhd.readapp.R;

public class WebViewActivity extends AppCompatActivity {


    private static final String TAG = "WebViewActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webwiew);

        String url = getIntent().getStringExtra("url");
        initView(url);
    }

    private void initView(String url) {
        mWebView = (WebView) findViewById(R.id.webwiew);
        mWebView.setWebViewClient(new MyWebViewClient());

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl(url);
    }



    class MyWebViewClient extends WebViewClient{

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
