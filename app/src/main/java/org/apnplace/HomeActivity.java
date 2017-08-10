package org.apnplace;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.JsonWebService;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.fragment.Fragment1;
import org.apnplace.fragment.Fragment2;
import org.apnplace.fragment.Fragment3;
import org.apnplace.model.ItemSlideMenu;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SELECT_FILE1 = 1;
    GridView myGrid;
    public static String[] pgmNameList = {"Orientation", "Program Information & Key Personnel", "Calendar", "Handbook", "Training & Educational Material",
            "Resources", "Data Tracking for Preceptors & Students", "Telehealth", "Networking & Support"};
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static int[] pgmImages = {R.drawable.module3books, R.drawable.module1pic3new, R.drawable.calnew, R.drawable.cimg1new,
            R.drawable.training, R.drawable.resour, R.drawable.datatnew, R.drawable.telehealth, R.drawable.module4new};
    ImageView  headerimg;
    TextView headername,headerid;
    private static int RESULT_LOAD_IMAGE = 1;
    String scandinavianCharacters = "øæå";
    public static final String MylastPREFERENCES = "lastpage" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
         APNSApplication apnsApplication = (APNSApplication) getApplicationContext();
        setContentView(R.layout.activity_home);
        myGrid = (GridView) findViewById(R.id.gridView);
        myGrid.setAdapter(new Adapter(this, pgmNameList, pgmImages));
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView title=new TextView(getApplicationContext());
        title.setText("APN-PLACE");
        title.setBackgroundColor(Color.parseColor("#003258"));
        title.setPadding(0,20,0,0);
        title.setGravity(Gravity.CENTER_VERTICAL);
        title.setTextColor(Color.WHITE);
    //    toolbar.addView(title);

       ImageView img=new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.ic_search_white_24dp);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomeActivity.this,SearchActivity.class);
                in.putExtra("query","apn");
                startActivity(in);
            }
        });

        img.setPadding(300,0,0,0);

        LinearLayout linearLayout=new LinearLayout(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);
        linearLayout.setPadding(200,0,0,0);
        linearLayout.addView(title);
        linearLayout.addView(img);

toolbar.addView(linearLayout);
//        LinearLayout ll= (LinearLayout) findViewById(R.id.searchlayout);
//        toolbar.addView(ll);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
         this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
         View headerView=navigationView.getHeaderView(0);
          headerimg=(ImageView)headerView.findViewById(R.id.heaerimageView);
    headername=(TextView)headerView.findViewById(R.id.headername);
        headerid=(TextView)headerView.findViewById(R.id.headerid);
      headername.setText(apnsApplication.getFirstname());
        headerid.setText(apnsApplication.getUser_id());
        System.out.println("Circular image view from mapper: " + headername.getText().toString());
        System.out.println("Circular image view from apns: " + apnsApplication.getUser_id());
        //navimag=(ImageView)findViewById(R.id.navapns);
                new DownloadImageTask().execute("http://apnsplace.cs.odu.edu/images/profile/"+apnsApplication.getUser_name()+".jpg");

    }


     class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        public DownloadImageTask() {

        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            headerimg.setImageBitmap(result);
            headerimg.invalidate();
        }
    }

    class UploadTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(HomeActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading ...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
        // String urlParameters = "uname="+apnsApplication.getUser_name();
           /* return JsonWebService.call("http://apnsplace.org/UpdateUser.php?cmd=UpdatePicture&uname="+apnsApplication.getUser_name(), urlParameters);*/
           // return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/images/profile/uname="+apnsApplication.getUser_name(), urlParameters);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            System.out.println("Response from update image: " + result);
        }

    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Do you want to exit the app? ");
        builder.setPositiveButton("Stay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.finishAffinity(HomeActivity.this);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        builder.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                searchView.clearFocus();
                Intent in=new Intent(HomeActivity.this,SearchActivity.class);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SharedPreferences sharedPreferences;
        if (id == R.id.nav_camera) {
           Intent intent4=new Intent(HomeActivity.this,UploadProfile.class);
            startActivity(intent4);
        }else if(id==R.id.nav_profile){
            Intent intent=new Intent(HomeActivity.this,IntUserActivity.class);
            startActivity(intent);

        }
        else if(id==R.id.aboutUS){
            Intent intent=new Intent(HomeActivity.this,AboutUSActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.contactUS) {
            Intent intent1=new Intent(HomeActivity.this,ContactActivity.class);
            startActivity(intent1);

        }
//        else if(id==R.id.blog1){
//            Intent intent=new Intent(HomeActivity.this,CardViewActivity.class);
//            startActivity(intent);
//        }
        else if (id==R.id.logout){
            sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);

       }
        else if(id==R.id.nursing)
        {
            Intent intent=new Intent(HomeActivity.this,associActivity.class);
            intent.putExtra("url","https://www.odu.edu/nursing");
            startActivity(intent);
        }
        else if(id==R.id.health)
        {
            Intent intent=new Intent(HomeActivity.this,associActivity.class);
            intent.putExtra("url","http://www.vdh.virginia.gov/");
            startActivity(intent);
        }
        else if(id==R.id.telehealth)
        {
            Intent intent=new Intent(HomeActivity.this,associActivity.class);
            intent.putExtra("url","https://uvahealth.com/services/telemedicine-telehealth-services");
            startActivity(intent);
        }
        else if(id==R.id.UVAnursing)
        {
            Intent intent=new Intent(HomeActivity.this,associActivity.class);
            intent.putExtra("url","https://www.nursing.virginia.edu/");
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}








