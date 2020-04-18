package com.sysumach.machtv;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.sysumach.machtv.base.BaseFragment;

public class BlogFragment extends BaseFragment {

    private WebView wb;
    private ProgressBar progressBar;
    private static final int MAX_VALUE = 100;
    private static final String BLOG_URL = "https://helloworldsysu.github.io";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        progressBar = bindView(R.id.pb_progress);
        wb = bindView(R.id.wb_blog);
        WebSettings webSettings = wb.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        progressBar.setMax(MAX_VALUE);
        wb.loadUrl(BLOG_URL);
        wb.setWebChromeClient(mWebChromeClient);
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
            if(newProgress == MAX_VALUE){
                progressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };
}
