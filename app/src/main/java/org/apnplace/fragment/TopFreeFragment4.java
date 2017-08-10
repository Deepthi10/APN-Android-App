package org.apnplace.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.apnplace.QuizAdapter;
import org.apnplace.QuizAdapterfour;
import org.apnplace.R;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.Answers;
import org.apnplace.model.QuizrootResponse;
import org.apnplace.utility.QuizrootDetails;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFreeFragment4 extends Fragment {
    QuizAdapterfour quizAdapterfour;
    ListView lv;
    public static Map questionMap;
    APNSApplication apnsApplication;
    public String userId;
    SharedPreferences sharedPreferences;
    long sMillisUntilFinished;
    CountDownTimer countDownTimer;
    long time;
    int flag=0;
    public String quizid, sizeQuiz,moduleid;
    Button Submit;
    public TopFreeFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowview= inflater.inflate(R.layout.fragment_top_free_fragment4, container, false);
        lv=(ListView)rowview.findViewById(R.id.listviewquizdisplay4);
        apnsApplication=(APNSApplication)getActivity().getApplicationContext();
        userId=apnsApplication.getUser_id();
        System.out.println("User id for the app " + userId);
        questionMap=new HashMap<String,String>();
        sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        Submit=(Button)rowview.findViewById(R.id.submitList4);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendQuizData();
            }
        });
        setRetainInstance(true);
        new QuizTask().execute("");
        return rowview;
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
            String urlParameters = "";
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/preceptor/ModuleGetDetails.php?UserAct=QUIZAUTOMOBILE&ModuleName=38", urlParameters);
        }


        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
           // result="{\"quizroot\":[{\"question_id\":\"121\",\"question\":\"The Four Types of Communication Styles are:\",\"answer_opt\":[{\"opt_id\":\"287\",\"answer_option\":\"Aggressive, passive, direct, in-direct\"},{\"opt_id\":\"288\",\"answer_option\":\"Passive aggressive, aggressive, assertive, passive\"},{\"opt_id\":\"289\",\"answer_option\":\"Driver, relator, aggressor, wallflower\"},{\"opt_id\":\"290\",\"answer_option\":\"Aggressive, passive, relator, driver\"}]},{\"question_id\":\"122\",\"question\":\"Ineffective communication can lead to :\",\"answer_opt\":[{\"opt_id\":\"291\",\"answer_option\":\"Cause fear\"},{\"opt_id\":\"292\",\"answer_option\":\"Cause confusion\"},{\"opt_id\":\"293\",\"answer_option\":\"Cause disorder\"},{\"opt_id\":\"294\",\"answer_option\":\" All of the above\"}]},{\"question_id\":\"123\",\"question\":\" The One Minute Preceptor Model includes all steps listed except:\",\"answer_opt\":[{\"opt_id\":\"295\",\"answer_option\":\"Commitment from learner\"},{\"opt_id\":\"296\",\"answer_option\":\" Self-Evaluation\"},{\"opt_id\":\"297\",\"answer_option\":\"Teach the general rules\"},{\"opt_id\":\"298\",\"answer_option\":\"Correct Errors\"}]},{\"question_id\":\"124\",\"question\":\"Effective Communication is:\",\"answer_opt\":[{\"opt_id\":\"299\",\"answer_option\":\"Brief, timely, clear, cogent\"},{\"opt_id\":\"300\",\"answer_option\":\"Therapeutic, self-aware, emotionally intelligent\"},{\"opt_id\":\"301\",\"answer_option\":\"Complete, clear, brief, timely\"},{\"opt_id\":\"302\",\"answer_option\":\"None of the above\"}]}]}";
            try {
                System.out.println("QuizDetails from quizautomobile---------:= " + result);
                ObjectMapper mapper = new ObjectMapper();
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("QuizDetails resultstring---------:= " + resultString);
                final QuizrootResponse quizResponse = mapper.readValue(resultString, QuizrootResponse.class);
                System.out.println("Quiz Response from quizautomobile---------:= " + quizResponse.getQuizroot().size());
                List<QuizrootDetails> quizroot=quizResponse.getQuizroot();
                quizAdapterfour=new QuizAdapterfour(TopFreeFragment4.this,quizroot);
                lv.setAdapter(quizAdapterfour);


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
                ans.setModuleid("38");
                moduleid= ans.getModuleid();
                ObjectMapper mapper= new ObjectMapper();
                try{
                    final String response=mapper.writeValueAsString(answers);
                    System.out.println(response);
                    System.out.println(questionMap.size());
                    if(questionMap.size()==0){
                        AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                        alert.setTitle("Message");
                        alert.setMessage("You haven't answered any question.Do you wish to continue?");
                        alert.setCancelable(false);
                        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new TopFreeFragment4.SendAnswers().execute("38",response);
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=alert.create();
                        alertDialog.show();
                    }
                    else{
                        new TopFreeFragment4.SendAnswers().execute("38",response);
                    }
                } catch (JsonGenerationException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
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
            String urlParameters = "moduleid=38"+params[0]+"answers"+params[1]+"&user_id="+apnsApplication.getUser_id();
            System.out.println("Response from urlparameteres score:---------- " + params[0]);
            System.out.println("Response from second param score:---------- " + params[1]);
            System.out.println("Response from third score:---------- " + apnsApplication.getUser_id());
            return JsonWebService.call("http://apnsplace.cs.odu.edu/preceptor/ModuleScore.php", urlParameters);
        }
        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from quizmodule score:---------- " + result);

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("Score");
            alert.setMessage("Your" + result);
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
}
