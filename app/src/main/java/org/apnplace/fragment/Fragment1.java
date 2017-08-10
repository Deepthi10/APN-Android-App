package org.apnplace.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apnplace.R;

public class Fragment1 extends Fragment {

    private String mParam1;
    private String mParam2;


    public Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View rootView=inflater.inflate(R.layout.fragment1,container,false);

        return rootView;
    }



}
