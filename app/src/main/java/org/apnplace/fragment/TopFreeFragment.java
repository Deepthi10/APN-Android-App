package org.apnplace.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import org.apnplace.QuizAdapter;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.model.Answers;
import org.apnplace.model.QuizrootResponse;
import org.apnplace.R;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.ResultResponse;
import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.ScorDetails;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFreeFragment extends Fragment {

    QuizAdapter quizAdapter;
    ListView lv;
    Button Submit;
    public static Map questionMap;
   APNSApplication apnsApplication;
    public String userId;
    SharedPreferences sharedPreferences;
    long sMillisUntilFinished;
    CountDownTimer countDownTimer;
    long time;
    int flag=0;
    public String Score,moduleid;
    public String msg;
   TextView newscore,topscore,attempt_count;
    View cardView;
    CardView viewroot;
    int lastscore;
    public TopFreeFragment() {
        // Required empty public constructor
    }
    //This fragment is for   Module Quiz
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState==null)
            questionMap = new HashMap<String, String>();
        View rootview= inflater.inflate(R.layout.fragment_top_free, container, false);
         apnsApplication=(APNSApplication)getActivity().getApplicationContext();
        userId=apnsApplication.getUser_id();
        moduleid=apnsApplication.getModuleid();
        System.out.println("User id for the app " + userId);
       // questionMap=new HashMap<String,String>();
        sharedPreferences = this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);

        return rootview;
    }

    @Override
    public void onViewCreated(View rootview, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootview, savedInstanceState);

        lv=(ListView)rootview.findViewById(R.id.listviewquizdisplay);
        newscore=(TextView)rootview.findViewById(R.id.newscoretv);
        topscore=(TextView)rootview.findViewById(R.id.topscoretv);
        attempt_count=(TextView)rootview.findViewById(R.id.attempt_count);
        cardView=rootview.findViewById(R.id.childcard);
        viewroot=(CardView)rootview.findViewById(R.id.card_root);
        Submit=(Button)rootview.findViewById(R.id.submitquizlist);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendQuizData();
            }
        });
     //Get the quiz data
        new QuizTask().execute("");
    }

    class QuizTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog dialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(getActivity());
            dialog.setMessage("loading...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters=""+params[0];
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/preceptor/ModuleGetDetails.php?UserAct=QUIZAUTOMOBILE&ModuleName="+apnsApplication.getModuleid()+"&user_id="+apnsApplication.getUser_id(), urlParameters);
        }


        @Override
        protected void onPostExecute(String result) {

            int paddingTop = lv.getPaddingTop();
            int paddingBottom=lv.getPaddingBottom();
            int paddingLeft=lv.getPaddingLeft();
            int paddingRight=lv.getPaddingRight();
            dialog.dismiss();
                System.out.println("QuizDetails from quizautomobile---------:= " + result);

            try {
                ObjectMapper mapper = new ObjectMapper();
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("QuizDetails resultstring---------:= " + resultString);
                final QuizrootResponse quizResponse = mapper.readValue(resultString, QuizrootResponse.class);
                System.out.println("Quiz Response from quizautomobile---------:= " + quizResponse.getQuizroot().size());
                List<QuizrootDetails>quizroot=quizResponse.getQuizroot();
                ScorDetails scoredetails=quizResponse.getScoredetails();
                System.out.println("Quiz for new user---------:= " + quizResponse.getScoredetails().getRecent_score());

                if (scoredetails.getRecent_score() == null){
                    viewroot.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    lv.setPadding(paddingLeft,0,paddingRight,paddingBottom);

                }else{
                    newscore.setText("Last Score :" + scoredetails.getRecent_score());
                    System.out.println("Quiz score for include---------:= " + scoredetails.getRecent_score());
                    topscore.setText("Top Score :" + scoredetails.getFinal_score());
                    attempt_count.setText(scoredetails.getAttempt_count());
                    lastscore= Integer.parseInt(scoredetails.getRecent_score());
                    if(lastscore<80) {
                        cardView.setBackgroundColor(Color.RED);
                    }
                    else{
                        cardView.setBackgroundColor(Color.GREEN);
                    }
                }

                quizAdapter=new QuizAdapter(TopFreeFragment.this,quizroot);
                lv.setAdapter(quizAdapter);


            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void mapQuesAns(int qid, int optionid) {
        questionMap.put(qid, optionid);
    }

    public void sendQuizData(){
     SharedPreferences.Editor editor=sharedPreferences.edit();
     editor.putString("Time","0");
     editor.commit();
     final List<Answers.UserAnswers>answers=new ArrayList<Answers.UserAnswers>();
     final Answers ans=new Answers();
     for(Object key:questionMap.keySet()){
         Log.i("Map", "qid-" + key + "  option id-" + questionMap.get(key));
         Answers.UserAnswers ua=new Answers.UserAnswers();
         ua.setQuestion_id(key.toString());
         ua.setOpt_id(questionMap.get(key).toString());
         answers.add(ua);
         System.out.println("get the qtn id:---------- " + ua.getQuestion_id());
         System.out.println("get the ans id:---------- " + ua.getOpt_id());
     }
     System.out.println("before alert");
     AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
     alert.setTitle("View Score");
     alert.setCancelable(false);
     alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
             // finish();
         }
     });
     alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

         @Override
         public void onClick(DialogInterface dialog, int which) {
             ans.setAnswer_opt(answers);
             final String response = urlEncodeUTF8(questionMap);
             System.out.println("Response Sent to server "+response);
                 System.out.println(questionMap.size());
                 if(questionMap.size()==0){
                     AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                     alert.setTitle("Message");
                     alert.setMessage("You haven't answered any question.Do you wish to continue?");
                     alert.setCancelable(false);
                     alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {

                         }
                     });
                     alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             new SendAnswers().execute(moduleid,userId,response);
                             System.out.println("Response from to server "+Score);
                             dialog.cancel();
                         }
                     });
                     AlertDialog alertDialog=alert.create();
                     alertDialog.show();
                 }
                 else{
                     new SendAnswers().execute(moduleid,userId,response);

                 }


         }
     });
     AlertDialog alertDialog = alert.create();
     alertDialog.show();
 }
    static String urlEncodeUTF8(Map<?,?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?,?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    class SendAnswers extends AsyncTask<String, Integer, String>{
        private ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            dialog.setMessage("sending answers...");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
           // APNSApplication apnsApplication=(APNSApplication)getActivity().getApplicationContext();
            String urlParameters = "moduleid="+params[0]+"&user_id="+params[1]+"&"+params[2];
            System.out.println("Response from urlparameteres score:---------- " + params[0]);
            System.out.println("Response from second param score:---------- " + params[1]);
            System.out.println("Response from third score:---------- " + apnsApplication.getUser_id());
            Log.d("", "@@@@ URL parameters " + urlParameters);
            return JsonWebService.call("http://apnsplace.cs.odu.edu/preceptor/ModuleScore.php", urlParameters);
        }
        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from quizmodule score:---------- " + result);
            try {

                ObjectMapper mapper = new ObjectMapper();
                //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("postDetails from resultpost:= " + result);
                final ResultResponse resResponse = mapper.readValue(resultString, ResultResponse.class);
                Score= resResponse.getScore();

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("Score");
            alert.setMessage("Your Score is : " + Score);
            alert.setCancelable(false);
            alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }
    }

/*    class Scorecard extends AsyncTask<String, Integer, String>{
        private ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            dialog.setMessage("sending answers...");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "user_id="+params[0]+"&moduleid="+params[1];
            System.out.println("Response from urlparameteres score:---------- " + params[0]);
            System.out.println("Response from second param score:---------- " + params[1]);
            System.out.println("Response from third score:---------- " + apnsApplication.getUser_id());
            Log.d("", "@@@@ URL parameters " + urlParameters);
            return JsonWebService.call("http://apnsplace.cs.odu.edu/preceptor/ScoreFinalSummary.php", urlParameters);
        }
        @Override
        protected void onPostExecute(String result) {

            int paddingTop = lv.getPaddingTop();
            int paddingBottom=lv.getPaddingBottom();
            int paddingLeft=lv.getPaddingLeft();
            int paddingRight=lv.getPaddingRight();

            dialog.dismiss();
            System.out.println("Response from quizmodule score:---------- " + result);
            try {

                ObjectMapper mapper = new ObjectMapper();
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("postDetails from resultpost:= " + result);
                if (result.contains("msg")) {
                    viewroot.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    lv.setPadding(paddingLeft,0,paddingRight,paddingBottom);

                }else{
                    final ScoreResponse scoreResponse=mapper.readValue(resultString,ScoreResponse.class);
                    List<ScorDetails> scoredetails=  scoreResponse.getScoredetails();
                    if(userId.equals(scoredetails.get(0).getU_id())) {
                        newscore.setText("Last Score :" + scoredetails.get(0).getScore());
                        topscore.setText("Top Score :" + scoredetails.get(0).getFinalscore());
                        lastscore= Integer.parseInt(scoredetails.get(0).getScore());
                        //viewroot.setVisibility(View.GONE);
                        if(lastscore<80) {
                            cardView.setBackgroundColor(Color.RED);
                        }
                        else{
                            cardView.setBackgroundColor(Color.GREEN);
                        }
                    }

                }

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/
}