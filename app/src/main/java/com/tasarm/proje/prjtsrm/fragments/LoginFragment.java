package com.tasarm.proje.prjtsrm.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tasarm.proje.prjtsrm.activitys.Login;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.activitys.Register_Screen;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,
                container, false);
        // Inflate the layout for this fragment
        Button kayıt=(Button)view.findViewById(R.id.button2);
        Button giris=(Button)view.findViewById(R.id.button3);

        kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Register_Screen.class));
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Login.class));
            }
        });

        return view;
    }

}
