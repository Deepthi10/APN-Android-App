package org.apnplace.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apnplace.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment4 extends Fragment {
    WebView webView;

    public HomeFragment4() {
        // Required empty public constructor
    }

    //Fragment for webview
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_home_fragment4, container, false);
        webView = (WebView) rootview.findViewById(R.id.webview4);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("http://apnsplace.cs.odu.edu/preceptor/ModuleFour.php");

        return rootview;
    }

}
