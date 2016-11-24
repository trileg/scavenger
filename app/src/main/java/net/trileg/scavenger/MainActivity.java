package net.trileg.scavenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://www.google.co.jp");

        webView.getSettings().setJavaScriptEnabled(true);
    }
}
