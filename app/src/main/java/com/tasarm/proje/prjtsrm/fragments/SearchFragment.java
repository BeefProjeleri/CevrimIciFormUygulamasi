package com.tasarm.proje.prjtsrm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.adapters.PostAdapter;
import com.tasarm.proje.prjtsrm.classs.MyVolley;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;
import com.tasarm.proje.prjtsrm.models.PostModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    String url_post;
    RecyclerView recyclerView;
    ArrayList<PostModel> data=new ArrayList<>();
    PostAdapter adapter;
    EditText text;

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        text=view.findViewById(R.id.text);
        btn=view.findViewById(R.id.btnsearch);
        recyclerView=view.findViewById(R.id.searchrecyl);
        String base=getString(R.string.adres);
        url_post=base+"/android/search.php";
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degis();
            }
        });

        return view;
    }

    public void degis(){

        data.clear();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_post,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject;
                            for(int i=0;i<jsonArray.length();i++){

                                jsonObject=jsonArray.getJSONObject(i);
                                data.add(new PostModel(jsonObject.getString("post_konu"),jsonObject.getString("post_tarih"),jsonObject.getString("post_gonderen"),jsonObject.getString("post_id"),jsonObject.getString("post_icerik"),jsonObject.getString("com_sayi"),jsonObject.getString("like_sayi"),jsonObject.getString("dislike"),jsonObject.getString("durum")));
                            }
                            text.setText("");
                            adapter=new PostAdapter(getContext(),data);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("post_istek", SharedPreferencesDurum.getMyStringPref(getContext()));
                params.put("text", text.getText().toString());


                return params;
            }
        };
        MyVolley.getInstance(getContext()).addToRequestque(stringRequest);
    }

}
