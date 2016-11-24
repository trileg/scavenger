package net.trileg.scavenger;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    boolean firstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomWebView webView = new CustomWebView(this);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        relativeLayout.addView(
                webView,
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT)
        );

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (firstLoad) {
                    // 初めてページが読み込まれたら，ここの処理を行う
                    view.loadUrl("https://www.firefly.kutc.kansai-u.ac.jp/");
                    firstLoad = false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // ページ読み込みが終わったら，ここの処理を行う
                firstLoad = true;
            }
        });

        webView.loadUrl("https://www.google.co.jp");

        webView.getSettings().setJavaScriptEnabled(true);
    }
}
