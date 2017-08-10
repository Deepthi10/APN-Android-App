package org.apnplace.fragment;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apnplace.BlogAdapter;
import org.apnplace.BlogCommentActivity;
import org.apnplace.BlogHomeActivity;
import org.apnplace.QuizAdapter;
import org.apnplace.R;
import org.apnplace.constants.APNSApplication;
import org.apnplace.constants.httpURLConnectionGet;
import org.apnplace.model.BlogResponse;
import org.apnplace.model.QuizResponse;
import org.apnplace.utility.BlogDetails;
import org.apnplace.utility.QuizDetails;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.closeButton;
import static org.apnplace.R.id.container;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    APNSApplication apnsApplication;
    WebView webView;
    String scandinavianCharacters = "øæå?";
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Fragment for webview
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_home, container, false);
        apnsApplication=(APNSApplication)getActivity().getApplicationContext();
        webView = (WebView) rootview.findViewById(R.id.webquiz1);
        webView.loadData(scandinavianCharacters, "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setAppCacheEnabled(true); webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("ISO-8859-1");
        webView.loadUrl("http://apnsplace.cs.odu.edu/preceptor/"+apnsApplication.getFiledetails()+".php");

        return rootview;
    }

}
