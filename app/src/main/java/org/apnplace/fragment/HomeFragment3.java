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
public class HomeFragment3 extends Fragment {

    WebView webView3;
    public HomeFragment3() {
        // Required empty public constructor
    }

    //Fragment for webview
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=  inflater.inflate(R.layout.fragment_home_fragment3, container, false);
        webView3 = (WebView) rootview.findViewById(R.id.WebviewMod3);
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView3.loadUrl("http://apnsplace.cs.odu.edu/preceptor/ModuleThree.php");
        return rootview;
    }

}
