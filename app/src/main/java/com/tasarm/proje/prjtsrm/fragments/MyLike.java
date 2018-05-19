package com.tasarm.proje.prjtsrm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Activities that contain this fragment must implement the
 * {@link MyLike.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyLike#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyLike extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public MyLike() {
        // Required empty public constructor

    }
    String isim;
    public void degis(String isim){
        this.isim=isim;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyLike.
     */
    // TODO: Rename and change types and number of parameters
    public static MyLike newInstance(String param1, String param2) {
        MyLike fragment = new MyLike();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String url_post;//http://10.0.2.2/android/post_select.php
    RecyclerView recyclerView;
    ArrayList<PostModel> data=new ArrayList<>();
    PostAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_like, container, false);

        String base=getString(R.string.adres);
        url_post=base+"/android/mylike_mydislike.php";
        recyclerView=(RecyclerView)view.findViewById(R.id.recyl);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

                        } catch (JSONException e) {
                            // Toast.makeText(context,"hata::."+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"hat:::."+error.toString(),Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("post_istek",isim);// SharedPreferencesDurum.getMyStringPref(getContext())


                return params;
            }
        };
        MyVolley.getInstance(getContext()).addToRequestque(stringRequest);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           // throw new RuntimeException(context.toString()
           //         + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
