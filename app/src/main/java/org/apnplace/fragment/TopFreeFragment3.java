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
import org.apnplace.QuizAdapterThree;
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
public class TopFreeFragment3 extends Fragment {

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
    QuizAdapterThree quizAdapterAdapterThree;
    public TopFreeFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_top_free_fragment3, container, false);
        apnsApplication=(APNSApplication)getActivity().getApplicationContext();
        userId=apnsApplication.getUser_id();
        System.out.println("User id for the app " + userId);
        questionMap=new HashMap<String,String>();
        sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        lv=(ListView)rootview.findViewById(R.id.listviewquizdisplay3);
        Submit=(Button)rootview.findViewById(R.id.submitquizbutton3);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendQuizData();
            }
        });
        setRetainInstance(true);
        new QuizTask().execute("");
        return rootview;
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
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/preceptor/ModuleGetDetails.php?UserAct=QUIZAUTOMOBILE&ModuleName=37", urlParameters);
        }


        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            //result="{\"quizroot\":[{\"question_id\":\"112\",\"question\":\"Preceptors should consider their students learning styles when planning learning experiences.\",\"answer_opt\":[{\"opt_id\":\"264\",\"answer_option\":\"True\"},{\"opt_id\":\"265\",\"answer_option\":\"False\"}]},{\"question_id\":\"113\",\"question\":\"Adult learners do not feel the need to apply what they have learned immediately to practice.\",\"answer_opt\":[{\"opt_id\":\"266\",\"answer_option\":\"True\"},{\"opt_id\":\"267\",\"answer_option\":\"False\"}]},{\"question_id\":\"114\",\"question\":\"Clinical preceptors can help adult learners be successful by doing all of the following except:\",\"answer_opt\":[{\"opt_id\":\"268\",\"answer_option\":\"Serving and an expert role model\"},{\"opt_id\":\"269\",\"answer_option\":\"Draw on the students past experiences\"},{\"opt_id\":\"270\",\"answer_option\":\"Allow the students to plan their own learning experiences\"},{\"opt_id\":\"271\",\"answer_option\":\" Provide memorable learning experiences\"},{\"opt_id\":\"272\",\"answer_option\":\" Provide relevant experiences related to learning outcomes\"}]},{\"question_id\":\"115\",\"question\":\"When planning for a student's clinical experience preceptors should remember that individuals typically have only one learning style.\",\"answer_opt\":[{\"opt_id\":\"273\",\"answer_option\":\"True\"},{\"opt_id\":\"274\",\"answer_option\":\"False\"}]},{\"question_id\":\"116\",\"question\":\" Kolb's Learning Cycle is based on the following 4-stages of learning: feeling, watching, thinking and doing.\",\"answer_opt\":[{\"opt_id\":\"275\",\"answer_option\":\"True\"},{\"opt_id\":\"276\",\"answer_option\":\"False\"}]},{\"question_id\":\"117\",\"question\":\"Honey and Munford adapted the Kolb Learning Theory and identified the following four styles of learning: visual, auditory, reading, and kinesthetic.\",\"answer_opt\":[{\"opt_id\":\"277\",\"answer_option\":\"True\"},{\"opt_id\":\"278\",\"answer_option\":\"False\"}]},{\"question_id\":\"118\",\"question\":\"You should allow the student that is a reflective and theoretical learner additional time to process new material before encouraging them to practice the newly acquired knowledge.\",\"answer_opt\":[{\"opt_id\":\"279\",\"answer_option\":\"True\"},{\"opt_id\":\"280\",\"answer_option\":\"False\"}]},{\"question_id\":\"119\",\"question\":\"It is the responsibility of the preceptor to adjust their teaching style, when possible, to the needs of the learner.\",\"answer_opt\":[{\"opt_id\":\"281\",\"answer_option\":\"True\"},{\"opt_id\":\"282\",\"answer_option\":\"False\"}]},{\"question_id\":\"120\",\"question\":\"Kinesthetic learners process information best when it can be touched. Examples of teaching strategies include which of the following:\",\"answer_opt\":[{\"opt_id\":\"283\",\"answer_option\":\" Written assignments\"},{\"opt_id\":\"284\",\"answer_option\":\" Taking notes\"},{\"opt_id\":\"285\",\"answer_option\":\"Examination of objects\"},{\"opt_id\":\"286\",\"answer_option\":\" All of the above\"}]}]}";
            try {
                System.out.println("QuizDetails from quizautomobile---------:= " + result);
                ObjectMapper mapper = new ObjectMapper();
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("QuizDetails resultstring---------:= " + resultString);
                final QuizrootResponse quizResponse = mapper.readValue(resultString, QuizrootResponse.class);
                System.out.println("Quiz Response from quizautomobile---------:= " + quizResponse.getQuizroot().size());
                List<QuizrootDetails> quizroot=quizResponse.getQuizroot();
                quizAdapterAdapterThree=new QuizAdapterThree(TopFreeFragment3.this,quizroot);
                lv.setAdapter(quizAdapterAdapterThree);


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
                ans.setModuleid("37");
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
                                new TopFreeFragment3.SendAnswers().execute("36",response);
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=alert.create();
                        alertDialog.show();
                    }
                    else{
                        new TopFreeFragment3.SendAnswers().execute("37",response);
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
            String urlParameters = "moduleid=37"+params[0]+"answers"+params[1]+"&user_id="+apnsApplication.getUser_id();
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
