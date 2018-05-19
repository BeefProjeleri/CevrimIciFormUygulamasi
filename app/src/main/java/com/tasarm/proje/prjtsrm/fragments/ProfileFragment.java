package com.tasarm.proje.prjtsrm.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tasarm.proje.prjtsrm.activitys.Login;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.adapters.PagerAdapter;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  implements MyComment.OnFragmentInteractionListener,MyShared.OnFragmentInteractionListener,MyLike.OnFragmentInteractionListener{

    Button cikis,giris;
    TextView textView;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        cikis= (Button) view.findViewById(R.id.cikis);
        textView=(TextView) view.findViewById(R.id.textView);
        giris=(Button)view.findViewById(R.id.button);
        textView.setText(SharedPreferencesDurum.getMyStringPref(getContext()));

        if(SharedPreferencesDurum.getMyBoolPref(getContext())){
            giris.setVisibility(View.INVISIBLE);

        }
        else
        {
            cikis.setVisibility(View.INVISIBLE);

        }
        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.tabBar);
        tabLayout.addTab(tabLayout.newTab().setText("HAREKETLERİM"));
        tabLayout.addTab(tabLayout.newTab().setText("PAYLAŞIMLARIM"));
        tabLayout.addTab(tabLayout.newTab().setText("YORUMLARIM"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=(ViewPager)view.findViewById(R.id.page);
        PagerAdapter adapter=new PagerAdapter(getFragmentManager(),tabLayout.getTabCount(),textView.getText().toString());
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
        cikis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              SharedPreferencesDurum.setMyBoolPref(getContext(),false);
                SharedPreferencesDurum.setMyStringPref(getContext(),"giriş yapılmadı");
                Toast.makeText(getContext(),"çıkış yapıldı",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(),Login.class));
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Login.class));
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
