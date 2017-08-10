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
public class HomeFragment2 extends Fragment {
    WebView webView2;

    public HomeFragment2() {
        // Required empty public constructor
    }

    //Fragment for webview
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_home_fragment2, container, false);
        webView2 = (WebView) rootview.findViewById(R.id.WebViewMod2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView2.loadUrl("http://apnsplace.cs.odu.edu/preceptor/ModuleTwo.php");
        return rootview;
    }

}
