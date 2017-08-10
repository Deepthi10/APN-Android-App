package org.apnplace;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;

public class PostComment extends AppCompatActivity {
    TextView Comments;
    EditText pcomment;
    Button postComment;
    TextView user_id;
    TextView pcid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_post_comment);
        Comments= (TextView) findViewById(R.id.postcomment);
        pcomment=(EditText)findViewById(R.id.pcomments);
        postComment=(Button)findViewById(R.id.commentbutton);
        user_id=(TextView)findViewById(R.id.comment_userid);
        pcid=(TextView)findViewById(R.id.comment_pid);

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentTask().execute(pcomment.getText().toString(), user_id.getText().toString(),pcid.getText().toString());
            }
        });
       // showActionBar();
    }
   /* private void showActionBar() {
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

        actionBar.setTitle("APNS-PLACE");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(PostComment.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu, menu);
       return super.onCreateOptionsMenu(menu);
   }
    class CommentTask extends AsyncTask<String, Integer, String>
    {
        private ProgressDialog dialog=new ProgressDialog(PostComment.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Posting...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            APNSApplication apnsApplication=(APNSApplication)getApplicationContext();
            String urlParameters = "opt=5"+"&comment="+params[0]+"&user_id="+apnsApplication.getUser_id()+"&pid="+apnsApplication.getPid();
            System.out.println("Comment req:" +urlParameters);
            return JsonWebService.call("http://apnsplace.cs.odu.edu/blog/mobile_services/MobileServices.php", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            //Toast.makeText(getApplicationContext(), "CommentDetails........" +result, Toast.LENGTH_LONG).show();
            System.out.println("CommentDetails........-----:" +result);
            finish();
            //startActivity(new Intent(PostComment.this,BlogCommentActivity.class));

        }

    }
}
