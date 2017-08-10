package org.apnplace;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.apnplace.fragment.Feedback1;
import org.apnplace.fragment.FourthFrag;
import org.apnplace.fragment.HomeFragment;
import org.apnplace.fragment.HomeFragment2;
import org.apnplace.fragment.TopFreeFragment;
import org.apnplace.fragment.TopFreeFragment2;
import org.apnplace.fragment.TopPaidFragment;
import org.apnplace.fragment.TopPaidFragment2;

public class TabBarModule2 extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getSupportActionBar().hide();
        setContentView(R.layout.activity_tab_bar_module2);
        System.out.println("tab bar module 2");
        toolbar=(Toolbar)findViewById(R.id.toolbars);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout2);
        viewPager=(ViewPager)findViewById(R.id.viewpager2);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment2(),"Module two");
        viewPagerAdapter.addFragments(new TopFreeFragment2(),"Quiz");
        viewPagerAdapter.addFragments(new TopPaidFragment2(),"Notes");
        viewPagerAdapter.addFragments(new Feedback1(),"Feedback");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
