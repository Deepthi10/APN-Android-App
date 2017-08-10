package org.apnplace;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.apnplace.constants.httpURLConnectionGet;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class Faq extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getSupportActionBar().hide();
        setContentView(R.layout.activity_faq);
        new FaqTask().execute("");
       //showActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                searchView.clearFocus();
                Intent in=new Intent(Faq.this,SearchActivity.class);
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
        editor.putString("lastpage","Faq");
        editor.commit();
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Faq.this,CardViewActivity.class);
        startActivity(intent);
    }
    class FaqTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(Faq.this);
        private ListActivity activity;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "";
            return httpURLConnectionGet.call("http://apnplace.org/blog/BlogServices.php?opt=16", urlParameters);
        }



        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();

        }

        }

        }
