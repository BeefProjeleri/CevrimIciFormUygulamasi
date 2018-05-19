package com.tasarm.proje.prjtsrm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class TrendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public TrendFragment() {
        // Required empty public constructor
    }

    String url_post;
    RecyclerView recyclerView;
    ArrayList<PostModel> data=new ArrayList<>();
    PostAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trend, container, false);
       recyclerView= view.findViewById(R.id.trendrcyl);
        swipeRefreshLayout=view.findViewById(R.id.trendswipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        String base=getString(R.string.adres);
        url_post=base+"/android/trend.php";

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         trend_cek();
        return view;
    }
    public void trend_cek(){

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

                            adapter=new PostAdapter(getContext(),data);
                            recyclerView.setAdapter(adapter);
                            swipeRefreshLayout.setRefreshing(false);

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


                return params;
            }
        };
        MyVolley.getInstance(getContext()).addToRequestque(stringRequest);

    }

    @Override
    public void onRefresh() {
        trend_cek();
    }
}
