package org.apnplace;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apnplace.fragment.Feedback1;
import org.apnplace.fragment.FourthFrag3;
import org.apnplace.fragment.FourthFrag4;
import org.apnplace.fragment.HomeFragment3;
import org.apnplace.fragment.HomeFragment4;
import org.apnplace.fragment.TopFreeFragment;
import org.apnplace.fragment.TopFreeFragment3;
import org.apnplace.fragment.TopFreeFragment4;
import org.apnplace.fragment.TopPaidFragment3;
import org.apnplace.fragment.TopPaidFragment4;

public class WebModuleActivity4 extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_web_module4);
        toolbar=(Toolbar)findViewById(R.id.toolbars);
       //  setSupportActionBar(toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout4);
        viewPager=(ViewPager)findViewById(R.id.viewpager4);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment4(),"Module Four");
        viewPagerAdapter.addFragments(new TopFreeFragment4(),"Quiz");
        viewPagerAdapter.addFragments(new TopPaidFragment4(),"Notes");
        viewPagerAdapter.addFragments(new Feedback1(),"Feedback");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
