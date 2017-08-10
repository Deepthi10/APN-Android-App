package org.apnplace;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;

public class ReplyMsg extends AppCompatActivity {

    TextView reply;
    EditText replyed;
    TextView qid;
    TextView uid;
    Button ReplyBuuton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_msg);
        reply=(TextView)findViewById(R.id.replytv);
        replyed=(EditText)findViewById(R.id.edReply);
        qid=(TextView)findViewById(R.id.reply_qid);
        uid=(TextView)findViewById(R.id.reply_uid);
        ReplyBuuton=(Button)findViewById(R.id.replyButton);
        ReplyBuuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ReplyMsg.this,"replied",Toast.LENGTH_LONG).show();
                new ReplyTask().execute(replyed.getText().toString(), uid.getText().toString(), qid.getText().toString());
            }
        });

    }
    class ReplyTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(ReplyMsg.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
           final APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
            String urlParameters = "opt=11"+"&reply="+params[0]+"&uid="+apnsApplication.getUser_id()+"&qid="+apnsApplication.getQuestion_id();
            return JsonWebService.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from reply--- :" + result);
            finish();
        }
    }
}
