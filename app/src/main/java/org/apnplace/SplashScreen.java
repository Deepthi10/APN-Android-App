package org.apnplace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.model.Response;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=1000;
    GoogleCloudMessaging gcm;
    String regid;
    String project_number="649305164238";
     SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);


//        Log.i("sss---",""+getSharedPreferences("apnuserId",Context.MODE_PRIVATE)+getSharedPreferences("apnpassword",Context.MODE_PRIVATE));
//        Log.i("sss---",""+sharedPreferences.getString("apnuserId","")+"==="+sharedPreferences.getString("apnpassword",""));

                sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
                Log.i("sss---",""+sharedPreferences.getString("apnuserId","")+"==="+sharedPreferences.getString("apnpassword",""));
                if(!sharedPreferences.getString("apnuserId","").equals("") && !sharedPreferences.getString("apnpassword","").equals(""))
                {
                    Log.i("sss---if",""+sharedPreferences.getString("apnuserId","")+"==="+sharedPreferences.getString("apnpassword",""));
                    getRegId();
                }

                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            finish();
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                        }
                    },SPLASH_TIME_OUT);
                }



       // showActionBar();




    }
    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            private ProgressDialog dialog = new ProgressDialog(SplashScreen.this);

            @Override
            protected void onPreExecute() {
                dialog.setMessage("loading...");
                dialog.show();
            }
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(project_number);
                    msg = "Device registered, registration ID=" + regid;
                    System.out.println("Device registered, registration ID= " + msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                dialog.cancel();
                sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
                new MatchingTask().execute(sharedPreferences.getString("apnuserId",""), sharedPreferences.getString("apnpassword",""),regid);
                System.out.println("Response from msg: " + msg);

            }
        }.execute(null, null, null);
    }
    private class MatchingTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(SplashScreen.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "uname="+params[0]+"&pwd="+params[1]+"&deviceToken="+params[2]+"&deviceType=Android";
            System.out.println(urlParameters);
            System.out.println("Logindetails req: " + urlParameters);
            return JsonWebService.call("http://apnsplace.cs.odu.edu/checkLogin.php", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from result: " + result);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                // response=(Response)mapper.readValues();
                System.out.println("Logindetails from resultstring:= " + result);
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("Logindetails from resultstring: " + result);
                final Response response = mapper.readValue(resultString, Response.class);

                if(response.getResponse().getStat().getStatus().equals("1")){
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    final APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
                    apnsApplication.setUser_id(response.getResponse().getUdetails().getUser_id());
                    apnsApplication.setUser_name(response.getResponse().getUdetails().getUser_name());
                    apnsApplication.setFirstname(response.getResponse().getUdetails().getFirstname());
                    startActivity(intent);

                }
                else if(!response.getResponse().getStat().getStatus().equals("1")){
                    //Toast.makeText(getApplicationContext(), "Invalid user" , Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alert=new AlertDialog.Builder(SplashScreen.this);
                    alert.setTitle("Alert!");
                    alert.setMessage("Invalid Username/Password.\nPlease try again.");
                    alert.setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void showActionBar() {
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
    }
}
