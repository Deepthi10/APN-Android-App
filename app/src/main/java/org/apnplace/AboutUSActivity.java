package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewFlipper;

import static org.apnplace.HomeActivity.MylastPREFERENCES;
import static org.apnplace.SplashScreen.MyPREFERENCES;

public class AboutUSActivity extends AppCompatActivity {
    WebView web;
ViewFlipper viewFlipper;
    //About Us Page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_contactweb);
        web=(WebView)findViewById(R.id.WebViewContactus) ;
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        web.loadUrl("http://apnsplace.cs.odu.edu/aboutUs.php");
        web.setWebViewClient(new WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }});
       /* viewFlipper=(ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setOnClickListener(this);*/
       // showActionBar();
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
                searchView.clearFocus();
                Intent in=new Intent(AboutUSActivity
                        .this,SearchActivity.class);
                in.putExtra("query",query);
                startActivity(in);
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
       editor.putString("lastpage","AboutUSActivity");
        editor.commit();
    }

   /* @Override
    public void onClick(View v) {
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(3000);
        //viewFlipper.showNext();
    }*/
}
