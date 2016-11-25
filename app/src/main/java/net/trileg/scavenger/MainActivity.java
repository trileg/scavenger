package net.trileg.scavenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomWebView webView = new CustomWebView(this);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        relativeLayout.addView(
                webView,
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT)
        );

        webView.setWebViewClient(new CustomWebViewClient());
        webView.loadUrl("https://www.google.co.jp/");

        webView.getSettings().setJavaScriptEnabled(true);
    }
}
