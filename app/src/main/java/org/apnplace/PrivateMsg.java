package org.apnplace;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;

public class PrivateMsg extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView user_id;
    EditText topic;
    EditText question;
    EditText askto;
    Button postQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     getSupportActionBar().hide();
        setContentView(R.layout.activity_privatemsg);
        tv1 = (TextView) findViewById(R.id.topic);
        tv2 = (TextView) findViewById(R.id.question);
        tv3 = (TextView) findViewById(R.id.askto);
        topic = (EditText) findViewById(R.id.edtopic);
        question = (EditText) findViewById(R.id.edquestion);
        askto = (EditText) findViewById(R.id.edaskto);
        user_id=(TextView)findViewById(R.id.ques_userid);

        postQ = (Button) findViewById(R.id.postquestion);
        postQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PrivateMsg.this,"Posted.",Toast.LENGTH_LONG).show();
                new MessageTask().execute(topic.getText().toString(), question.getText().toString(), askto.getText().toString(), user_id.getText().toString());
                Intent intent= new Intent(PrivateMsg.this,QuestionsDisplay.class);
                startActivity(intent);
            }
        });
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
                Intent in=new Intent(PrivateMsg.this,SearchActivity.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(PrivateMsg.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    class MessageTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(PrivateMsg.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading matches...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
            String urlParameters = "opt=13"+"&topic="+params[0]+"&qcontent="+params[1]+"&askto="+params[2]+"&user_id="+apnsApplication.getUser_id();
            return JsonWebService.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php", urlParameters);

        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from questions: " + result);
         //   Toast.makeText(getApplicationContext(),"result from questions: " + result,Toast.LENGTH_LONG).show();
        }
    }
 }
