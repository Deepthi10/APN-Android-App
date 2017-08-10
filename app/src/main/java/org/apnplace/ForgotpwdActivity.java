package org.apnplace;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;

public class ForgotpwdActivity extends AppCompatActivity {

    TextView Currentpwd;
    TextView Newpwd;
    TextView Reenterpwd;
    EditText cpwd;
    EditText npwd;
    EditText re_npwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);

        Currentpwd=(TextView)findViewById(R.id.tvcurrentpwd);
        Newpwd=(TextView)findViewById(R.id.tvnewtpwd);
        Reenterpwd=(TextView)findViewById(R.id.tvreenterpwd);
        cpwd=(EditText)findViewById(R.id.edcurrentpwd);
        npwd=(EditText)findViewById(R.id.ednewpwd);
        re_npwd=(EditText)findViewById(R.id.ednreneterpwd);

        new ForgotpwdTask().execute();
        //showActionBar();

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

    class ForgotpwdTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(ForgotpwdActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
            String urlParameters = "opt=13"+"&topic="+params[0]+"&qcontent="+params[1]+"&askto="+params[2]+"&user_id="+apnsApplication.getUser_id();
            return JsonWebService.call("http://apnsplace.cs.odu.edu/blog/BlogServices.php", urlParameters);

        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from questions: " + result);
          //  Toast.makeText(getApplicationContext(), "result from questions: " + result, Toast.LENGTH_LONG).show();
        }
    }
}
