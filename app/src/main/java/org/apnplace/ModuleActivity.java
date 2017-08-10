package org.apnplace;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.BlogResponse;
import org.apnplace.model.ModuleResponse;
import org.apnplace.utility.BlogDetails;
import org.apnplace.utility.ModuleDetails;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apnplace.HomeActivity.MylastPREFERENCES;

public class ModuleActivity extends AppCompatActivity {

    ListView moduList;
    ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_module);
        moduList=(ListView)findViewById(R.id.listviewmodule);


        new ModuleTask().execute("");
    }
    class ModuleTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog = new ProgressDialog(ModuleActivity.this);
        private ListActivity activity;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "";
            return httpURLConnectionGet.call("http://apnsplace.cs.odu.edu/preceptor/ModuleGetDetails.php?UserAct=MODULEHOME", urlParameters);
        }



        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            //result="{\"modules\":[{\"moduleid\":\"35\",\"modulename\":\"Module 1: The Role of the Preceptor\",\"unlock_date\":\"2016-01-01 17:00:00\",\"moduledesc\":\"Expectations and qualities of outstanding preceptors\",\"filedetails\":\"ModuleOne\"},{\"moduleid\":\"36\",\"modulename\":\"Module 2: Communication Skills\",\"unlock_date\":\"2016-04-01 15:19:00\",\"moduledesc\":\"Providing Strategies to build relationships between preceptors, peers and their patients\\n\",\"filedetails\":\"ModuleTwo\"}]}";
           // result="{\"modules\":[{\"moduleid\":\"35\",\"modulename\":\"Module 1: The Role of the Preceptor\",\"unlock_date\":\"2016-01-01 17:00:00\",\"moduledesc\":\"Expectations and qualities of outstanding preceptors\",\"filedetails\":\"ModuleOne\",\"iconimg\":\"Module 1.png\"},{\"moduleid\":\"36\",\"modulename\":\"Module 2:Communication Skills\",\"unlock_date\":\"2016-04-01 15:19:00\",\"moduledesc\":\"Providing Strategies to build relationships between preceptors, peers and their patients\",\"filedetails\":\"ModuleTwo\",\"iconimg\":\"Module 2.png\"}]}";
            try {

                ObjectMapper mapper = new ObjectMapper();
                //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
//                DeserializerProvider provider = new StdDeserializerProvider()
//                        .withAdditionalKeyDeserializers(new CaseInsensitiveKeyDeserializers());
//                mapper.setDeserializerProvider(provider);
                String resultString = result.replaceAll("[^\\x00-\\x7F]", "");


                resultString=resultString.replaceAll("ModuleID","moduleid");
                resultString=resultString.replaceAll("Modules","modules");
                resultString=resultString.replaceAll("ModuleName","modulename");
                resultString=resultString.replaceAll("Unlock_Date","unlock_date");
                resultString=resultString.replaceAll("ModuleDesc","moduledesc");
                resultString=resultString.replaceAll("FileDetails","filedetails");
               resultString=resultString.replaceAll("Icon_Img","icon_img");
                resultString=resultString.replaceAll("Available","available");
                System.out.println("postDetails from resultpost:= " + resultString);


                final ModuleResponse modResponse = mapper.readValue(resultString, ModuleResponse.class);
                final APNSApplication apnsApplication=(APNSApplication) getApplicationContext();
                List<ModuleDetails> Modules = modResponse.getModules();
                moduleAdapter = new ModuleAdapter(ModuleActivity.this, Modules);
                moduList.setAdapter(moduleAdapter);
                System.out.println("Moduledetails from Url:" +Modules.size());
                moduList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ModuleDetails posts = (ModuleDetails) moduleAdapter.getItem(position);
                        if(posts.getAvailable().equals("1")) {
                            Intent intent = new Intent(ModuleActivity.this, TabBar.class);
                            intent.putExtra("moduleid", posts.getModuleid());
                            intent.putExtra("filedetails", posts.getFiledetails());
                            System.out.println("postDetails from Url:" + posts.getModuledesc());
                            apnsApplication.setModuleid(posts.getModuleid());
                            apnsApplication.setFiledetails(posts.getFiledetails());
                            SharedPreferences sharedPreferences;
                            sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("lastpage","TabBar");
                            editor.putString("moduleno",""+posts.getModuleid());
                            editor.putString("file",""+posts.getFiledetails());
                            editor.commit();
                            //Toast.makeText(getApplicationContext(), "clicked........" +posts.getModuleid(), Toast.LENGTH_LONG).show();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "This Module is not yet Active", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences(MylastPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastpage","ModuleActivity");
        editor.putString("page","page");

        editor.commit();
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
                Intent in=new Intent(ModuleActivity.this,SearchActivity.class);
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

}
