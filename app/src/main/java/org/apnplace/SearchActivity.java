package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class SearchActivity extends AppCompatActivity {
    private List<String> previous = new ArrayList<String>();
    private String mLastUrl,pageFinished;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         web= (WebView) findViewById(R.id.searchWebView);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        web.setWebViewClient(new WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("DebugDebug", "OnPageFinished " + url);
                pageFinished=url;
                mLastUrl = url;

                super.onPageFinished(view, url);
            }
        });
        web.loadUrl("http://apnplace.org/sphider-1.3.6/search.php?query="+getIntent().getStringExtra("query") +"&search=1");
        web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                WebView.HitTestResult hr  = ((WebView)view).getHitTestResult();
                System.out.println("url--"+hr);
                if (hr != null && mLastUrl != null) {
                    if (previous.isEmpty() || !previous.get(previous.size() - 1).equals(mLastUrl)) {
                        Log.i("DebugDebug", "url is---"+mLastUrl);
                        if(previous.size()>=1)
                            Log.i("DebugDebug"," prev url-----"+previous.get(previous.size() - 1));
                        previous.add(mLastUrl);
                    }
                    // Log.i("DebugDebug", "getExtra = " + hr.getExtra() + "\t\t Type = " + hr.getType());
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

               // Toast.makeText(getApplicationContext(),""+query,Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                web= (WebView) findViewById(R.id.searchWebView);
                web.getSettings().setJavaScriptEnabled(true);
                web.setWebViewClient(new WebViewClient(){

                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(request.toString());
                        return true;
                    }

                });
                web.loadUrl("http://apnplace.org/sphider-1.3.6/search.php?query="+query +"&search=1");

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","SearchActivity");
        editor.commit();
    }
    @Override
    public void onBackPressed() {
        int size = previous.size();

        Log.i("size","size is  "+size);
//       for(int i=previous.size();i>0;i--)
//            System.out.println("urls-----"+previous.get(i));
        if (size>0)
        {
            Log.i("size","size is --"+previous.get(size - 1));
             web= (WebView) findViewById(R.id.searchWebView);
            web.getSettings().setJavaScriptEnabled(true);
            web.getSettings().setPluginState(WebSettings.PluginState.ON);
            web.loadUrl(previous.get(size - 1));
            previous.remove(size - 1);
        }
        else
            super.onBackPressed();
//        super.onBackPressed();

    }

}
