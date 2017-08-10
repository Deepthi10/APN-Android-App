package org.apnplace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class MyProfileActivity extends AppCompatActivity {

    Button Profilepic;
    /*Button MyProgress;
    Button Profiledetails;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Profilepic=(Button)findViewById(R.id.changeprofilebutton);
      /*  MyProgress=(Button)findViewById(R.id.MyprogressButton);
        Profiledetails=(Button)findViewById(R.id.ProfiledetailsButton);*/

        Profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         /* Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);*/
                Intent intent= new Intent(MyProfileActivity.this,UploadProfile.class);
                startActivity(intent);

                //loadImagefromGallery(v);
            }
        });
      //  showActionBar();
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","MyProfileActivity");
        editor.commit();
    }
/*
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

        actionBar.setTitle("APNS-PLACE");
    }*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(MyProfileActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
