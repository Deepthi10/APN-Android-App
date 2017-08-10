package org.apnplace.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
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
import org.apnplace.QuizAdaptertwo;
import org.apnplace.R;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.Answers;
import org.apnplace.model.QuizResponse;
import org.apnplace.model.QuizrootResModtwo;
import org.apnplace.model.QuizrootResponse;
import org.apnplace.utility.QuizDetails;
import org.apnplace.utility.QuizrootDetModtwo;
import org.apnplace.utility.QuizrootDetails;
import org.apnplace.utility.QuizrootDetailstwo;
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
public class TopFreeFragment2 extends Fragment {
    QuizAdaptertwo quizAdaptertwo;
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
    public TopFreeFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_top_free_fragment2, container, false);
        apnsApplication=(APNSApplication)getActivity().getApplicationContext();
        userId=apnsApplication.getUser_id();
        System.out.println("User id for the app " + userId);
        questionMap=new HashMap<String,String>();
        sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        lv=(ListView)rootview.findViewById(R.id.listviewquizdisplay2);
        Submit=(Button)rootview.findViewById(R.id.submitquizbutton2);
        setRetainInstance(true);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendQuizData();
            }
        });
       new QuizTaskTwo().execute("");
        return rootview;
    }
    class QuizTaskTwo extends AsyncTask<String, Integer, String> {

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
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/preceptor/ModuleGetDetails.php?UserAct=QUIZAUTOMOBILE&ModuleName=36", urlParameters);
        }


        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
          //result="{\"quizroot\":[{\"question_id\":\"186\",\"question\":\"Characterized by an intent to have a deep, meaningful personal attachment to another person. May include physical intimacy.\",\"answer_opt\":[{\"opt_id\":\"537\",\"answer_option\":\"Social\"},{\"opt_id\":\"538\",\"answer_option\":\"Romantic\"},{\"opt_id\":\"539\",\"answer_option\":\"Therapeutic\"},{\"opt_id\":\"540\",\"answer_option\":\"Professional\"}]},{\"question_id\":\"187\",\"question\":\"Characterized by a one-way focus on the needs of a person who is receiving psychologic,behavioral, medical, or physical care by another person.\",\"answer_opt\":[{\"opt_id\":\"541\",\"answer_option\":\"Social\"},{\"opt_id\":\"542\",\"answer_option\":\"Romantic\"},{\"opt_id\":\"543\",\"answer_option\":\"Therapeutic\"},{\"opt_id\":\"544\",\"answer_option\":\"Professional\"}]},{\"question_id\":\"188\",\"question\":\"Characterized by a transformative processes in which individuals work together to achieve interrelated goals.\",\"answer_opt\":[{\"opt_id\":\"545\",\"answer_option\":\"Social\"},{\"opt_id\":\"546\",\"answer_option\":\"Romantic\"},{\"opt_id\":\"547\",\"answer_option\":\"Therapeutic\"},{\"opt_id\":\"548\",\"answer_option\":\"Professional\"}]},{\"question_id\":\"189\",\"question\":\"Characterized by mutual intent to pursue common interests through non-pressured and enjoyable interactions.\",\"answer_opt\":[{\"opt_id\":\"549\",\"answer_option\":\"Social\"},{\"opt_id\":\"550\",\"answer_option\":\"Romantic\"},{\"opt_id\":\"551\",\"answer_option\":\"Therapeutic\"},{\"opt_id\":\"552\",\"answer_option\":\"Professional\"}]},{\"question_id\":\"190\",\"question\":\"The preceptor provides specific feedback to the student about conducting the physical examination.\",\"answer_opt\":[{\"opt_id\":\"553\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"554\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"555\",\"answer_option\":\"Action\"},{\"opt_id\":\"556\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"191\",\"question\":\"The School of Nursing administrator or faculty member confirms that a clinical affiliation agreement is in place.\",\"answer_opt\":[{\"opt_id\":\"557\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"558\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"559\",\"answer_option\":\"Action\"},{\"opt_id\":\"560\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"192\",\"question\":\"The preceptor and the student complete the final performance evaluation together and agree to meet for coffee at a future professional conference.\",\"answer_opt\":[{\"opt_id\":\"561\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"562\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"563\",\"answer_option\":\"Action\"},{\"opt_id\":\"564\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"193\",\"question\":\"The preceptor specifies the clinic schedule and time frame as he\\/she accepts a student for the clinical rotation.\",\"answer_opt\":[{\"opt_id\":\"565\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"566\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"567\",\"answer_option\":\"Action\"},{\"opt_id\":\"568\",\"answer_option\":\"Termination\"},{\"opt_id\":\"569\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"570\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"571\",\"answer_option\":\"Action\"},{\"opt_id\":\"572\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"195\",\"question\":\"The student provides the preceptor with contact information, including telephone, text,and email.\",\"answer_opt\":[{\"opt_id\":\"573\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"574\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"575\",\"answer_option\":\"Action\"},{\"opt_id\":\"576\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"196\",\"question\":\"The preceptor and the student take a few moments at the end of the day to reflect on clinical progress and identify goals for the next day.\",\"answer_opt\":[{\"opt_id\":\"577\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"578\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"579\",\"answer_option\":\"Action\"},{\"opt_id\":\"580\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"197\",\"question\":\"The preceptor provides a short tour of the clinical setting for the student, and introduces the student to the staff.\",\"answer_opt\":[{\"opt_id\":\"581\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"582\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"583\",\"answer_option\":\"Action\"},{\"opt_id\":\"584\",\"answer_option\":\"Termination\"}]},{\"question_id\":\"198\",\"question\":\"Which of the following is least helpful in creating and sustaining a healthy preceptor-student relationship?\",\"answer_opt\":[{\"opt_id\":\"585\",\"answer_option\":\"The preceptor provides matter-of-fact verbal critique 'in the moment' to the student with patients, family, and\\/or staff present.\"},{\"opt_id\":\"586\",\"answer_option\":\"The preceptor maintains a warm, welcoming, and positive approach.\"},{\"opt_id\":\"595\",\"answer_option\":\"The preceptor exhibits empathy , patience, and understanding towards the student's learning process.\"}]},{\"question_id\":\"200\",\"question\":\"The preceptor observes and assesses the student's initial communications and comfort level in the clinic setting.\",\"answer_opt\":[{\"opt_id\":\"591\",\"answer_option\":\"Planning and Preparation\"},{\"opt_id\":\"592\",\"answer_option\":\"Orientation\"},{\"opt_id\":\"593\",\"answer_option\":\"Action\"},{\"opt_id\":\"594\",\"answer_option\":\"Termination\"}]}]}";
               try {
                   ObjectMapper mapper = new ObjectMapper();
                   String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                   System.out.println("QuizDetails resultstring---------:= " + resultString);
                   final QuizrootResponse quizResponse  = mapper.readValue(resultString, QuizrootResponse.class);
                   List<QuizrootDetails> quizroot = quizResponse.getQuizroot();
                   quizAdaptertwo=new QuizAdaptertwo(TopFreeFragment2.this,quizroot);
                   lv.setAdapter(quizAdaptertwo);
               }
               catch (JsonMappingException e) {
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
                ans.setModuleid("36");
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
                                new TopFreeFragment2.SendAnswers().execute("36",response);
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=alert.create();
                        alertDialog.show();
                    }
                    else{
                        new TopFreeFragment2.SendAnswers().execute("36",response);
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
            String urlParameters = "moduleid=36"+params[0]+"answers"+params[1]+"&user_id="+apnsApplication.getUser_id();
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
