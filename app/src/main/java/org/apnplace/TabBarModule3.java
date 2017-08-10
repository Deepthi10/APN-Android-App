package org.apnplace;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.apnplace.fragment.Feedback1;
import org.apnplace.fragment.FourthFrag;
import org.apnplace.fragment.FourthFrag3;
import org.apnplace.fragment.HomeFragment2;
import org.apnplace.fragment.HomeFragment3;
import org.apnplace.fragment.TopFreeFragment;
import org.apnplace.fragment.TopFreeFragment2;
import org.apnplace.fragment.TopFreeFragment3;
import org.apnplace.fragment.TopPaidFragment2;
import org.apnplace.fragment.TopPaidFragment3;

public class TabBarModule3 extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tab_bar_module3);
        toolbar=(Toolbar)findViewById(R.id.toolbars);
        System.out.println("tab bar module 3");
//        setSupportActionBar(toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout3);
        viewPager=(ViewPager)findViewById(R.id.viewpager3);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment3(),"Module three");
        viewPagerAdapter.addFragments(new TopFreeFragment3(),"Quiz");
        viewPagerAdapter.addFragments(new TopPaidFragment3(),"Notes");
        viewPagerAdapter.addFragments(new Feedback1(),"Feedback");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
