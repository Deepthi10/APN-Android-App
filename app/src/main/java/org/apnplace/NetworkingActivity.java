package org.apnplace;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class NetworkingActivity extends AppCompatActivity {
 Button post;
    TextView tx1;
    TextView tx2;
    TextView user_id;
    EditText Headline;
    EditText Contents;
    TextView date;
    EditText uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_networking);
        tx1= (TextView) findViewById(R.id.hdltextView);
        tx2= (TextView) findViewById(R.id.contx);
        user_id= (TextView) findViewById(R.id.user_id);
        Headline= (EditText) findViewById(R.id.headline);
        Contents= (EditText) findViewById(R.id.contents);
        date=(TextView)findViewById(R.id.postdate);
        post= (Button) findViewById(R.id.postbutton);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new MatchingTask().execute(Headline.getText().toString(), Contents.getText().toString(), user_id.getText().toString());
                Intent intent= new Intent(NetworkingActivity.this,CardViewActivity.class);
                startActivity(intent);
            }
        });
      //  showActionBar();
    }
   /* private void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#003258")));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.action_bar, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                )
        );

        actionBar.setTitle("APNS-PLACE");
    }*/
  /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(NetworkingActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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
               Intent in=new Intent(NetworkingActivity.this,SearchActivity.class);
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

    class MatchingTask extends AsyncTask<String, Integer, String>
    {
        private ProgressDialog dialog=new ProgressDialog(NetworkingActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Posting...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
            String urlParameters = "opt=4"+"&headline="+params[0]+"&content="+params[1]+"&date="+params[2]+"&user_id="+apnsApplication.getUser_id();
            System.out.println("Logindetails req:" +urlParameters);
            return JsonWebService.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from post: " +result);




        }

    }
}

