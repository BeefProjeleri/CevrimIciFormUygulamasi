package com.tasarm.proje.prjtsrm.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tasarm.proje.prjtsrm.fragments.LoginFragment;
import com.tasarm.proje.prjtsrm.fragments.PostFragment;
import com.tasarm.proje.prjtsrm.fragments.ProfileFragment;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.fragments.SearchFragment;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;
import com.tasarm.proje.prjtsrm.fragments.TrendFragment;

public class AnaSayfa extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("ÜNİVERSİTE POSTLARIM");
                    PostFragment fragment=new PostFragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fram,fragment,"PostFragment");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("TRENDLER");
                    TrendFragment fragment2=new TrendFragment();
                    FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fram,fragment2,"TrendFragment");
                    fragmentTransaction2.commit();
                    return true;


                case R.id.navigation_notifications:
                    if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){
                        startActivity(new Intent(getApplicationContext(),AddPostActivity.class));
                    }
                    else{
                        setTitle("GİRİŞ YAPMALISIN");
                        LoginFragment fragment1=new LoginFragment();
                        FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.fram,fragment1,"login");
                        fragmentTransaction1.commit();

                    }
                    return true;
                case R.id.search:
                    setTitle("SEARCH");
                    SearchFragment fragment3=new SearchFragment();
                    FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.fram,fragment3,"search");
                    fragmentTransaction3.commit();

                    return true;
                case R.id.profile:
                    if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){

                        setTitle("PROFİLE");
                        ProfileFragment fragment1=new ProfileFragment();
                        FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.fram,fragment1,"profile");
                        fragmentTransaction1.commit();
                    }else{
                        setTitle("GİRİŞ YAPMALISIN");
                        LoginFragment fragment1=new LoginFragment();
                        FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.fram,fragment1,"login");
                        fragmentTransaction1.commit();
                   }
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        setTitle("ÜNİVERSİTE POSTLARIM");
        PostFragment fragment=new PostFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram,fragment,"PostFragment");
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
