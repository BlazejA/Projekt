package com.example.ilewydalem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

public class oMnie extends AppCompatActivity {

    private WebView webView;
    Animation anim;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_mnie);
        txt = (TextView)findViewById(R.id.oMnietxt);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rolldown);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://github.com/BlazejA/Projekt");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        txt.startAnimation(anim);
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
