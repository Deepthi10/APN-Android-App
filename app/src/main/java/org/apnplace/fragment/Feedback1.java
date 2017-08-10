package org.apnplace.fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.FeedbackAdapter;
import org.apnplace.R;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.constants.connectionPost;
import org.apnplace.constants.httpURLConnectionGet;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback1 extends Fragment {

ListView feedbacklist;
    Button Submit;
    public Feedback1() {
        // Required empty public constructor
    }
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    EditText ed1,ed2;
    Spinner gender;
    Spinner maritalstatus;
    Spinner racespinner;
    Spinner educationspinner;
    private Map<String,String> genderMap=new HashMap<String,String>();
    private Map<String,String> raceMap=new HashMap<String,String>();
    private Map<String,String>maritalstatusMap= new HashMap<String,String>();
    private Map<String,String>educationMap=new HashMap<String,String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View rowview= inflater.inflate(R.layout.fragment_feedback1, container, false);
        genderMap.put("Select", "Select");
        genderMap.put("Strongly Agree", "Strongly Agree");
        genderMap.put("Agree", "Agree");
        genderMap.put("Neutral", "Neutral");
        genderMap.put("Disagree","Disagree");
        genderMap.put("Strongly Disagree","Strongly Disagree");

        raceMap.put("Select", "Select");
        raceMap.put("Strongly Agree", "Strongly Agree");
        raceMap.put("Agree", "Agree");
        raceMap.put("Neutral", "Neutral");
        raceMap.put("Disagree","Disagree");
        raceMap.put("Strongly Disagree","Strongly Disagree");

        maritalstatusMap.put("Select", "Select");
        maritalstatusMap.put("Strongly Agree", "Strongly Agree");
        maritalstatusMap.put("Agree", "Agree");
        maritalstatusMap.put("Neutral", "Neutral");
        maritalstatusMap.put("Disagree","Disagree");
        maritalstatusMap.put("Strongly Disagree","Strongly Disagree");

        educationMap.put("Select", "Select");
        educationMap.put("Strongly Agree", "Strongly Agree");
        educationMap.put("Agree", "Agree");
        educationMap.put("Neutral", "Neutral");
        educationMap.put("Disagree","Disagree");
        educationMap.put("Strongly Disagree","Strongly Disagree");

        tv1=(TextView)rowview.findViewById(R.id.genderTitle);
        tv2=(TextView)rowview.findViewById(R.id.raceTitle);
        tv3=(TextView)rowview.findViewById(R.id.EducationalLevelTitle);
        tv4=(TextView)rowview.findViewById(R.id.maritalTitle);
        tv5=(TextView)rowview.findViewById(R.id.tvfeed1);
        tv6=(TextView)rowview.findViewById(R.id.tvfeed2);

        ed1=(EditText)rowview.findViewById(R.id.editfeed1);
        ed2=(EditText)rowview.findViewById(R.id.editfeed2);

        gender=(Spinner)rowview.findViewById(R.id.genderSpinner) ;
        racespinner=(Spinner)rowview.findViewById(R.id.raceDropdown) ;
        educationspinner=(Spinner)rowview.findViewById(R.id.educationDropdown);
        maritalstatus=(Spinner)rowview.findViewById(R.id.maritalDropdown);

        Submit=(Button)rowview.findViewById(R.id.submitfeedback);


     /*   new QuizFeedbackTask().execute(gender.getSelectedItem().toString(), racespinner.getSelectedItem().toString(), maritalstatus.getSelectedItem().toString(),
                                    educationspinner.getSelectedItem().toString(),ed1.getText().toString(),ed2.getText().toString());*/
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender.getSelectedItem().equals("Select")||racespinner.getSelectedItem().equals("Select")||educationspinner.getSelectedItem().equals("Select")||maritalstatus.getSelectedItem().equals("Select")){
                    Toast.makeText(getActivity(), "Please choose an option", Toast.LENGTH_LONG).show();
                }
                else {
String feed1=ed1.getText().toString();
                    String feed2=ed2.getText().toString();
                    System.out.println(feed1+"--"+feed2);
                    new QuizFeedbackTask().execute(gender.getSelectedItem().toString(),racespinner.getSelectedItem().toString(),educationspinner.getSelectedItem().toString(),maritalstatus.getSelectedItem().toString(),feed1,feed2);
                }
            }
        });

        return rowview;
    }
    class QuizFeedbackTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog dialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("loading...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication=(APNSApplication)getActivity().getApplicationContext();
           // String urlParameters="moduleid="+apnsApplication.getModuleid()+"&user_id="+apnsApplication.getUser_id()+"&1=";
            HashMap map=new HashMap();
            map.put("moduleid",apnsApplication.getModuleid());
            map.put("user_id",apnsApplication.getUser_id());
            map.put("1",params[0]);
            map.put("2",params[1]);
            map.put("3",params[2]);
            map.put("4",params[3]);
            if(params[4]!=null)
            map.put("5",params[4]);
            if(params[5]!=null)
            map.put("6",params[5]);
            //map.put("1",gender.getSelectedItem().toString());
            return connectionPost.startService("http://apnsplace.cs.odu.edu/preceptor/ModuleFeedback.php",map);
        }


        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from feedback quiz:----------" + result);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Feedback Sent!");
            builder.setMessage("Thank you for the Feedback");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }

}
