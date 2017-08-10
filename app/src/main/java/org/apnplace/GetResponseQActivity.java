package org.apnplace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.AnswerResponse;
import org.apnplace.model.AnswerToQResponse;
import org.apnplace.utility.AnswerDetails;
import org.apnplace.utility.AnswerToQDetails;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class GetResponseQActivity extends AppCompatActivity {
    ListView answersList;
    String qid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_to_q);
        answersList= (ListView) findViewById(R.id.listviewanswers);
        TextView topic= (TextView) findViewById(R.id.topictitle);
        topic.setText(getIntent().getExtras().getString("topic"));
        TextView question= (TextView) findViewById(R.id.questionreply);
        question.setText(getIntent().getExtras().getString("question"));
        TextView date= (TextView) findViewById(R.id.qdatereply);
        date.setText(getIntent().getExtras().getString("qdate"));
TextView user= (TextView) findViewById(R.id.qusernamereply);
        user.setText(getIntent().getExtras().getString("quser"));
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(isNetworkStatusAvialable (getApplicationContext())) {
            new ReplyTask().execute(getIntent().getExtras().getString("question_id"));
            // new ReplyTask().execute("");
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pmsg, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pmsg:
                startActivity(new Intent(GetResponseQActivity.this,ReplyMsg.class));
                //Toast.makeText(this, "private message", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }
    class ReplyTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(GetResponseQActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
            String urlParameters = "qid="+params[0]+"&uname="+apnsApplication.getUser_name();
            System.out.println("get qn A req------: " + urlParameters);
            Log.i("private message comment","http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=20&qid="+params[0]+ urlParameters);
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=20&qid="+params[0], urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from reply to qn n anwers==----: " + result);
            ObjectMapper mapper = new ObjectMapper();
            try {
                AnswerResponse answerResponse = mapper.readValue(result, AnswerResponse.class);
                List<AnswerDetails> quest=answerResponse.getQuest();
                AnswerAdapter answerAdapteradapter = new AnswerAdapter(GetResponseQActivity.this, quest);
                answersList.setAdapter(answerAdapteradapter);
                Log.i("list size",""+quest.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
