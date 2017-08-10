package org.apnplace;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.BlogResponse;
import org.apnplace.utility.BlogDetails;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class CardViewActivity extends AppCompatActivity {
    //private RecyclerView mRecyclerView;
    ListView listView;
    MyAdapter mAdapter;
    //For displaying blogs in the form of cards
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        //mRecyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        listView=(ListView)findViewById(R.id.cardviewlistt);

        if(isNetworkStatusAvialable (getApplicationContext())) {
            new CardTask().execute("");
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post, menu);
        inflater.inflate(R.menu.pm,menu);
        inflater.inflate(R.menu.faq,menu);
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                searchView.clearFocus();
                Intent in=new Intent(CardViewActivity.this,SearchActivity.class);
                in.putExtra("query",query);
                startActivity(in);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_post:
                startActivity(new Intent(CardViewActivity.this, NetworkingActivity.class));
             //   Toast.makeText(this, "Post", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_pm:
                startActivity(new Intent(CardViewActivity.this,QuestionsDisplay.class));
              //  Toast.makeText(this, "Private Message", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_faq:
                startActivity(new Intent(CardViewActivity.this,Faq.class));
               // Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","CardViewActivity");
        editor.commit();
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(CardViewActivity.this,HomeActivity.class);
        startActivity(intent);
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
    class CardTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(CardViewActivity.this);
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
                //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");
                System.out.println("postDetails from resultpost:= " + result);
                final BlogResponse blogResponse = mapper.readValue(resultString, BlogResponse.class);
                List<BlogDetails> posts = blogResponse.getPosts();
                mAdapter = new MyAdapter(posts,CardViewActivity.this);
                listView.setAdapter(mAdapter);
                //Toast.makeText(getApplicationContext(), "you clicked" + posts.size(), Toast.LENGTH_SHORT).show();
               //final APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        BlogDetails posts = (BlogDetails)mAdapter.getItem(position);
                        System.out.println("print the position " + posts.getId());
                        Intent intent = new Intent(CardViewActivity.this, BlogCommentActivity.class);
                        intent.putExtra("id",posts.getId());
                        intent.putExtra("user_id",posts.getUser_id());
                        intent.putExtra("user_name",posts.getUser_name());
                        intent.putExtra("headline",posts.getHeadline());
                        intent.putExtra("content",posts.getContent());
                        intent.putExtra("date",posts.getDate());
                       startActivity(intent);

                    }
                });
               System.out.println("Logindetails from Url:" + posts.size());

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
