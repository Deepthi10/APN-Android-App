package org.apnplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FloatingActivity extends AppCompatActivity {
    WebView wefloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_floating);
        wefloat=(WebView)findViewById(R.id.oduweb);
        wefloat.getSettings().setJavaScriptEnabled(true);
        wefloat.getSettings().setPluginState(WebSettings.PluginState.ON);
        wefloat.setWebViewClient(new Callback());
        wefloat.loadUrl("http://www.odu.edu");
    }
    class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }
}
