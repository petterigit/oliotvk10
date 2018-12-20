package com.example.petteri.verkkoselain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView web;
    WebBackForwardList webHistory;
    EditText osoitepalkki;
    int history_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        osoitepalkki = findViewById(R.id.editText);
        web = findViewById(R.id.WebView);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        load_url("http://www.google.fi");
    }

    public void executeJS(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void refreshPage(View v) {
        web.reload();
    }
    public void load_url(String url) {
        web.loadUrl(url);
        history_index++;
    }

    public void prevPage(View v) {
        webHistory = web.copyBackForwardList();
        String prev_url = webHistory.getItemAtIndex(history_index-1).getUrl();
        history_index--;
        web.loadUrl(prev_url);
    }

    public void nextPage(View v) {
        webHistory = web.copyBackForwardList();
        String next_url = webHistory.getItemAtIndex(history_index+1).getUrl();
        history_index++;
        web.loadUrl(next_url);
    }

    public void gotoPage(View v) {
        String url = osoitepalkki.getText().toString();
        if (url.equals("index.html")){
            load_url("file:///android_asset/index.html");
            System.out.println("Avataan index");
            return;
        } else if  (!(url.startsWith("http://") || url.startsWith("https://"))) {
            url = "http://" + url;
        }
        load_url(url);
    }
}
