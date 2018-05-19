package com.tasarm.proje.prjtsrm.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.adapters.PagerAdapter;
import com.tasarm.proje.prjtsrm.fragments.MyComment;
import com.tasarm.proje.prjtsrm.fragments.MyLike;
import com.tasarm.proje.prjtsrm.fragments.MyShared;

public class ProfileActivity extends AppCompatActivity implements MyComment.OnFragmentInteractionListener,MyShared.OnFragmentInteractionListener,MyLike.OnFragmentInteractionListener{

    TextView klc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        klc=(TextView)findViewById(R.id.klc);
        Intent inet=getIntent();
        String isim=inet.getStringExtra("kullanıcı");

        klc.setText(isim);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabBar1);
        tabLayout.addTab(tabLayout.newTab().setText("HAREKETLERİM"));
        tabLayout.addTab(tabLayout.newTab().setText("PAYLAŞIMLARIM"));
        tabLayout.addTab(tabLayout.newTab().setText("YORUMLARIM"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.page1);
        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),isim);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
