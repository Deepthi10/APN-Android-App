package org.apnplace;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.connectionPost;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.PreceptorResponse;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;

import java.io.IOException;
import java.util.HashMap;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class IntUserActivity extends AppCompatActivity {
 TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,TextPD,tvedu;
    TextView tvf1,tvl2,tvr3,tveid4;
    EditText tvcetr5,tvjob6,tvcon7,eduedit;
    EditText workzipcode;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_int_user);

        TextPD=(TextView)findViewById(R.id.textViewpd);
        tv1=(TextView)findViewById(R.id.fnameprecdet);
        tvf1=(TextView)findViewById(R.id.fname);

        tv2=(TextView)findViewById(R.id.lnameprecdet);
        tvl2=(TextView)findViewById(R.id.lname);

        tv3=(TextView)findViewById(R.id.roletv);
        tvr3=(TextView)findViewById(R.id.Roletv);

        tvedu=(TextView)findViewById(R.id.eduleveltv);
        eduedit=(EditText)findViewById(R.id.eduleveledittext);


        tv4=(TextView)findViewById(R.id.contactemailtv);
        tveid4=(TextView) findViewById(R.id.contactemail);

        tv5=(TextView)findViewById(R.id.certificationtv);
        tvcetr5=(EditText)findViewById(R.id.Certitv);

        tv6=(TextView)findViewById(R.id.Curntjobtv);
        tvjob6=(EditText)findViewById(R.id.curntj);

        tv7=(TextView)findViewById(R.id.Concontacttv);
        tvcon7=(EditText)findViewById(R.id.Concontact);
         workzipcode= (EditText) findViewById(R.id.workzipcode);
 update= (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new update().execute(tvcon7.getText().toString(),eduedit.getText().toString(),tvcetr5.getText().toString(),tvjob6.getText().toString(),workzipcode.getText().toString());
            }
        });
        new UserDet().execute("");
    }
    class update extends AsyncTask<String, Integer, String>
    {
        private ProgressDialog dialog = new ProgressDialog(IntUserActivity.this);


        @Override
        protected void onPreExecute() {
            dialog.setMessage("updating profile...");
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            HashMap map=new HashMap();
            APNSApplication apnsApplication=(APNSApplication) getApplicationContext();
            map.put("uid",apnsApplication.getUser_id());
            map.put("contact",params[0]);
            map.put("edulevel",params[1]);
            map.put("certification",params[2]);
            map.put("currentjob",params[3]);
            map.put("workzip",params[4]);
            return connectionPost.startService("http://apnsplace.cs.odu.edu/preceptor/iosUpdateProfile.php",map);
        }
        protected void onPostExecute(String result) {

            dialog.dismiss();
            System.out.println("update result"+result);
            Toast.makeText(getApplicationContext(),"Profile Information Updated.",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(IntUserActivity.this,HomeActivity.class);
            startActivity(in);
        }

    }
    class UserDet extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(IntUserActivity.this);
        private ListActivity activity;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication=(APNSApplication) getApplicationContext();
            String urlParameters = "";
            System.out.println("userid from resultpost:=------- " + apnsApplication.getUser_id());
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/getuserdetails.php?CMD=MEDITPROFILEDETAILS&Key="+apnsApplication.getUser_id(), urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            // result="{\"status\":\"1\",\"userdetails\":{\"user_name\":\"preceptor1\",\"role\":\"Preceptor\",\"firstname\":\"Amanda\",\"lastname\":\"Morcom\",\"middlename\":\"M\",\"email_id\":\"ttriv002@odu.edu\",\"edulevel\":\"Nursing practitioner\",\"address\":\"dragas 112\",\"certification\":\"Nursing practioner\",\"zipsetting\":\"23509\",\"psetting\":\"234561\",\"currentjob\":\"Nurse Practioner Faculty\",\"contact\":\"7579998624\"}}";

            try {

                ObjectMapper mapper = new ObjectMapper();
                DeserializerProvider provider = new StdDeserializerProvider()
                        .withAdditionalKeyDeserializers(new CaseInsensitiveKeyDeserializers());
                mapper.setDeserializerProvider(provider);
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                result=result.replaceAll("UserDetails","userdetails");
                result=result.replaceAll("Edulevel","edulevel");
                result=result.replaceAll("Certification","certification");
                result=result.replaceAll("Psetting","psetting");
                result=result.replaceAll("CurrentJob","currentjob");
                result=result.replaceAll("Contact","contact");
                result=result.replaceAll("Address","address");
                result=result.replaceAll("zipSetting","zipsetting");
                System.out.println("Details from resultpost:=------- " + result);
                final PreceptorResponse pResponse = mapper.readValue(result, PreceptorResponse.class);
                System.out.println("precep Response from precepdetails---------:= " + pResponse.getUserdetails().getFirstname());
                System.out.println("status Response from precepdetails---------:= " + pResponse.getStatus());
                tvf1.setText(pResponse.getUserdetails().getFirstname());
                tvl2.setText(pResponse.getUserdetails().getLastname());
                tvr3.setText(pResponse.getUserdetails().getRole());
                eduedit.setText(pResponse.getUserdetails().getEdulevel());
                tveid4.setText(pResponse.getUserdetails().getEmail_id());
                tvcetr5.setText(pResponse.getUserdetails().getCertification());
                tvjob6.setText(pResponse.getUserdetails().getCurrentjob());
                tvcon7.setText(pResponse.getUserdetails().getContact());
                workzipcode.setText(pResponse.getUserdetails().getZipsetting());
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","IntUserActivity");
        editor.commit();
    }
}
