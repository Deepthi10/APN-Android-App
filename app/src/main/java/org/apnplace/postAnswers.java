package org.apnplace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.QuestionResponse;
import org.apnplace.utility.QuestionDetails;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link postAnswers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link postAnswers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class postAnswers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView alv;
    private OnFragmentInteractionListener mListener;

    public postAnswers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment postAnswers.
     */
    // TODO: Rename and change types and number of parameters
    public static postAnswers newInstance(String param1, String param2) {
        postAnswers fragment = new postAnswers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_post_answers, container, false);
        alv= (ListView) myView.findViewById(R.id.listviewanswer);
        new AnswerTask().execute("");
        return myView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    class AnswerTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final APNSApplication apnsApplication = (APNSApplication) getActivity().getApplicationContext();
            String urlParameters = "uid=" + apnsApplication.getUser_id();
            System.out.println("Logindetails req:" + urlParameters);
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=14&uname=" + apnsApplication.getUser_name(), urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.cancel();
            System.out.println("answers : " + result);

            try {
                ObjectMapper mapper = new ObjectMapper();
                QuestionResponse qResponse = mapper.readValue(result, QuestionResponse.class);
                List<QuestionDetails> quest = qResponse.getQuest();
                alv= (ListView) getActivity().findViewById(R.id.listviewanswer);
                final QuestAdapter questAdapter = new QuestAdapter(getActivity().getApplicationContext(), quest);
                alv.setAdapter(questAdapter);
                //Toast.makeText(getApplicationContext(), "size of quest" + quest.size(), Toast.LENGTH_SHORT).show();
                final APNSApplication apnsApplication = (APNSApplication) getActivity().getApplicationContext();
                alv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        QuestionDetails qtndet = (QuestionDetails) questAdapter.getItem(position);
                        Intent intent = new Intent(getActivity(), GetResponseQActivity.class);
                        intent.putExtra("question_id", qtndet.getQuestion_id());
                        intent.putExtra("question", qtndet.getQuestion());
                        intent.putExtra("topic",qtndet.getTopic());
                        intent.putExtra("qdate", qtndet.getDate());
                        intent.putExtra("quser", qtndet.getUser_name());
                        apnsApplication.setQuestion_id(qtndet.getQuestion_id());
                        //Toast.makeText(getApplicationContext(),"clicked on question"+qtndet.getQuestion_id(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
