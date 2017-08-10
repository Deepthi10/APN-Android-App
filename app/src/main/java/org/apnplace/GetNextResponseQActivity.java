package org.apnplace;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.AnswerToQResponse;
import org.apnplace.utility.AnswerToQDetails;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class GetNextResponseQActivity extends AppCompatActivity {
    ListView answersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_next_response_q);
        new ReplyToReplyTask().execute();
    }
    class ReplyToReplyTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(GetNextResponseQActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
            String urlParameters = "qid="+params[0];
            System.out.println("get qn A req------: " + urlParameters);
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=10&qid="+apnsApplication.getQuestion_id(), urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("Response from reply to replyy n anwers==----: " + result);
            ObjectMapper mapper = new ObjectMapper();
            try {
                AnswerToQResponse answerToQResponseResponse = mapper.readValue(result, AnswerToQResponse.class);
                List<AnswerToQDetails> quest=answerToQResponseResponse.getQuest();
                AnswerToQAdapter answerTAdapteradapter = new AnswerToQAdapter(GetNextResponseQActivity.this, quest);
                answersList.setAdapter(answerTAdapteradapter);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
