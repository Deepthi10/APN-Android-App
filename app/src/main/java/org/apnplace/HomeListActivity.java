package org.apnplace;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class HomeListActivity extends AppCompatActivity {

    ListView myList;

    public String[] listName = {"Invite", "Calender Events", "Interested Users", "My Questions", "Activity List", "Preceptor Home",
            "Logindetails Activity Dashboard"};
    public String[] listDetail = {"Invite prospective Preceptors,Mentors,or new Faculty to join APN place",
            "Calender of events & training activities", "Approve or Reject Interested users who are interested to Join Apn place",
            "Questions posed to you by preceptors in the blog area", "Monitor Progress of Preceptors", "Jump to the Preceptor Home",
            " Statictical Representation of Logindetails Activities"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(new ListAdapter(this, listName, listDetail));
        //showActionBar();
    }
  /*  private void showActionBar() {
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(HomeListActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
