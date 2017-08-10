package org.apnplace;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class TrainingActivity extends AppCompatActivity {
    GridView gv;
    public static String[] modNameList = {"Module 1:The Role of Preceptor", "Module 2:Healthy Student Preceptor Relationship",
            "Module 3:Precepting & Learning Styles","Module 4:Communication Skills","Module 5:Student Evaluation & Feedback","Module 6:Conflict Resolution",
            "Module 7:Precepting with Technology","Module 8:Problem Solving","Module 9:Cultural Competancy","Module 10:The Adult Learner/Reflective Practise",
            "Module 11:Experimental & Problem-based Learning","Module 12:Interprofessional Collabration"};

    public static String[] modDetail = {"Expectations & Qualities of Outstanding Preceptors", "Practical methods preceptors can utilize to facilitate preceptor-student relationships",
            "Precepting and learning styles and how they can impact student outcomes", "Providong Strategies","providing feedback","The ability of feedback",
    "Using Technology","Enhance the ability","The ability to respect","Techniques for precepting","Utilizing experimanetal","Utilizing Integrated"};

   /* public static int[] modImages = {R.drawable.mod1, R.drawable.mod2, R.drawable.mod3,R.drawable.mod4,R.drawable.mod5,R.drawable.mod6,R.drawable.mod7,
   R.drawable.mod8 ,R.drawable.mod9,R.drawable.mod10,R.drawable.mod11,R.drawable.mod12};*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_home_training);
        gv = (GridView) findViewById(R.id.gdVw);
        //gv.setAdapter(new TrainAdapter(this, modNameList, modDetail, modImages));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(), modNameList[position], Toast.LENGTH_SHORT).show();
            }
        });
    //showActionBar();
    }
    /*private void showActionBar() {
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
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TrainingActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
