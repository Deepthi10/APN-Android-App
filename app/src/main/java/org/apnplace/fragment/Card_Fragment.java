package org.apnplace.fragment;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apnplace.MyAdapter;
import org.apnplace.R;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.BlogResponse;
import org.apnplace.utility.BlogDetails;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Card_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    public Card_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_card_, container, false);
        mRecyclerView=(RecyclerView)rootview.findViewById(R.id.my_recycler_view);
        new CardTask().execute("");
        return  rootview;
    }
    //Gets the data in the card view
    class CardTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(getContext());
        private ListActivity activity;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "";
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=24&Offset=0", urlParameters);
        }
        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            try {

                ObjectMapper mapper = new ObjectMapper();
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("postDetails from resultpost:= " + result);
                final BlogResponse blogResponse = mapper.readValue(resultString, BlogResponse.class);
                List<BlogDetails> posts = blogResponse.getPosts();
                //MyAdapter mAdapter = new MyAdapter(posts,getContext());
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
               // mRecyclerView.setAdapter(mAdapter);
                //mAdapter.setOnCardClickListner((MyAdapter.OnCardClickListner) this);
                System.out.println("LoginDetails from Url:" + posts.size());

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
}
