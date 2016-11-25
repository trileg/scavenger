package net.trileg.scavenger;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


class Param {
    WebView webView = null;
    String url = "";
    String htmlSource = "";
    String ua = "";
}

class CustomWebViewClient extends WebViewClient {

    boolean firstLoad = true;
    Param param;

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (firstLoad) {
            // 初めてページが読み込まれたら，ここの処理を行う
            Log.d("Debug", "onPageStarted: "+url);
            param = new Param();
            param.url = url;
            param.webView = view;
            param.ua = view.getSettings().getUserAgentString();

            DownloadTask task = new DownloadTask();
            task.execute(param);
            firstLoad = false;
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // ページ読み込みが終わったら，ここの処理を行う
        firstLoad = true;
    }
}

class DownloadTask extends AsyncTask<Param, Integer, Param> {
    @Override
    protected Param doInBackground(Param... params) {
        Param param = params[0];

        try {
            // ここでJsoup使ってDOMの編集を行う
            Document document = Jsoup.connect(param.url).userAgent(param.ua).get();
            Log.d("Debug", "charset: "+document.charset());

            Element current = document.getElementById("current");
            if (param.url.contains("www.firefly.kutc.kansai-u.ac.jp") && current != null) {
                Log.d("Test", "title before: "+document.getElementsByTag("title"));
                Element title = document.select("title").first();
                title.text("Secure Information System Lab.");
                Log.d("Test", "title after: "+document.getElementsByTag("title"));

                current.text("現在地");
            }

            param.htmlSource = document.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return param;
    }

    @Override
    protected void onPostExecute(Param result) {
        if (result.htmlSource.equals("")){
            Log.d("Debug", "htmlSource is empty");
        } else {
            Log.d("Debug", "loadDataWithBaseURL");

            result.webView.loadDataWithBaseURL(result.url, result.htmlSource, "text/html", "UTF-8", result.url);
        }
    }
}
