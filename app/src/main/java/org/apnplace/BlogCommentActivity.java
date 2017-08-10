package org.apnplace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.CommentResponse;
import org.apnplace.utility.CommentsDetails;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class

   BlogCommentActivity extends AppCompatActivity {
    ListView lv;
    String content;
    String headline;
    String id;
    String user_id,user_name,date;
    String img;
   // List<String> blogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_comment);
        ActionBar myActionBar = getSupportActionBar();
       lv= (ListView) findViewById(R.id.commentlist);
        Bundle bundle=getIntent().getExtras();

       // new CommentTask().execute("");
        Log.d("", "onCreate: " + bundle);

        if (bundle != null) {

            id = bundle.getString("id");
        }
        //id=getIntent().getExtras().getString("id");
        user_id=getIntent().getExtras().getString("user_id");
        user_name=getIntent().getExtras().getString("user_name");
        headline=getIntent().getExtras().getString("headline");
        content=getIntent().getExtras().getString("content");
        date=getIntent().getExtras().getString("date");

        //Toast.makeText(getApplicationContext(),"here is the pid on post click"+id,Toast.LENGTH_LONG).show();

        /*blogs.add(id);
        blogs.add(user_id);
        blogs.add(user_name);
        blogs.add(headline);
        blogs.add(content);
        blogs.add(date);*/
        TextView headlineblog= (TextView) findViewById(R.id.headlineblog);
        headlineblog.setText(headline);
        TextView contentblog= (TextView) findViewById(R.id.contentblog);
        contentblog.setText(content);
        TextView dateblog= (TextView) findViewById(R.id.dateblog);
        dateblog.setText(date);
        TextView nameblog= (TextView) findViewById(R.id.nameblog);
        nameblog.setText(user_name);
        ImageView imageblog= (ImageView) findViewById(R.id.imageblog);
        img=user_name;
        String uri=img.replace(" ","%20");
        System.out.println("uri in the imagey----:" +uri);
        new DownloadImageTask(imageblog).execute("http://apnsplace.cs.odu.edu/images/profile/"+uri+".jpg");
        System.out.println("Content from the list:" + headline+content+date+user_name);
        showActionBar();
        myActionBar.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(isNetworkStatusAvialable (getApplicationContext())) {
            new CommentTask().execute("");
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","BlogCommentActivity");
        editor.commit();
    }
  private void showActionBar() {
      ActionBar actionBar = getSupportActionBar();
      actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#003258")));
      actionBar.setDisplayShowCustomEnabled(true);
      actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
      actionBar.setCustomView(getLayoutInflater().inflate(R.layout.action_bar, null),
              new ActionBar.LayoutParams(
                      ActionBar.LayoutParams.WRAP_CONTENT,
                      ActionBar.LayoutParams.MATCH_PARENT,
                      Gravity.CENTER
              )
      );

      actionBar.setTitle(" ");
  }
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post, menu);
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_post:
                startActivity(new Intent(BlogCommentActivity.this, PostComment.class));
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

    class CommentTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(BlogCommentActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication= (APNSApplication) getApplicationContext();
            apnsApplication.setId(id);
           /* apnsApplication.setUser_name(getIntent().getExtras().getString("user_name"));
            apnsApplication.setHeadline(getIntent().getExtras().getString("headline"));
            apnsApplication.setContent(getIntent().getExtras().getString("content"));
            apnsApplication.setDate(getIntent().getExtras().getString("date"));*/
            apnsApplication.setPid(apnsApplication.getId());
            String urlParameters="";
            //String urlParameters = "pid="+apnsApplication.getId();
            System.out.println("pid req:" + apnsApplication.getPid());
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php?opt=3&pid="+apnsApplication.getPid(), urlParameters);
    }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from comments: " + result);
            ObjectMapper mapper = new ObjectMapper();
            try {
               CommentResponse commentResponse = mapper.readValue(result, CommentResponse.class);
                List<CommentsDetails> posts=commentResponse.getPosts();
               // CommentAdapter cadapter = new CommentAdapter(BlogCommentActivity.this, posts,blogs);
                CommentAdapter cadapter = new CommentAdapter(BlogCommentActivity.this,posts);
                lv.setAdapter(cadapter);

            } catch (IOException e) {
                e.printStackTrace();
            }
            }
    }
}
