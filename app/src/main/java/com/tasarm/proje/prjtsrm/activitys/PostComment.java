package com.tasarm.proje.prjtsrm.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.adapters.CommentAdapter;
import com.tasarm.proje.prjtsrm.classs.MyVolley;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;
import com.tasarm.proje.prjtsrm.models.CommentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostComment extends AppCompatActivity {

   String id,baslik,icerik,tarih,gonderen,com_sayi;
   TextView textView,konu,trh,gondern,icrk,comnt,dis,like;
    EditText comment;
    Button gonder;
    RecyclerView rcyl;
    ArrayList<CommentModel> data=new ArrayList<>();
   CommentAdapter adapter;
    String url_comment;//10.0.2.2.
    String url_addcomment;
    String url_durum;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);
       textView=(TextView)findViewById(R.id.textView5);
       konu=(TextView)findViewById(R.id.konu);
       trh=(TextView)findViewById(R.id.tarih);
       gondern=(TextView)findViewById(R.id.gonderen);
       icrk=(TextView)findViewById(R.id.icerik);
       comnt=(TextView)findViewById(R.id.textView11);
       like=(TextView)findViewById(R.id.like);
       dis=(TextView)findViewById(R.id.dis);
        rcyl= (RecyclerView) findViewById(R.id.rcyl);
        comment=(EditText)findViewById(R.id.comment);
        gonder=(Button)findViewById(R.id.gonder);
        String base=getString(R.string.adres);
        url_comment=base+"/android/comment_select.php";
        url_addcomment=base+"/android/add_comment.php";
        url_durum=base+"/android/like_dislike.php";
        if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())==false){

            comment.setVisibility(View.INVISIBLE);
            gonder.setVisibility(View.INVISIBLE);

        }
        gondern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){
                    Intent inet=new Intent(getApplicationContext(),ProfileActivity.class);
                    inet.putExtra("kullanıcı",gondern.getText().toString());
                    startActivity(inet);
                }
                else{
                    Toast.makeText(getApplicationContext(),"giriş yapmalısın",Toast.LENGTH_LONG).show();

                }
            }
        });

       Intent intent = getIntent();
      id= intent.getStringExtra("id");
      baslik= intent.getStringExtra("baslik");
      icerik= intent.getStringExtra("icerik");
      tarih= intent.getStringExtra("tarih");
      gonderen= intent.getStringExtra("gonderen");
        com_sayi=intent.getStringExtra("com_sayi");
        String k=intent.getStringExtra("durum");

        comnt.setText(com_sayi);
       textView.setText(id);
       konu.setText(baslik);
       trh.setText(tarih);
       gondern.setText(gonderen);
       icrk.setText(icerik);
        dis.setText(intent.getStringExtra("dis"));
        like.setText(intent.getStringExtra("like"));
        if(k.equals("0")){

            t="0";
            dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darkdislike1, 0);

            //dis
        }
        else if(k.equals("1")){
            t="1";
            like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darklike1, 0);
            //like
        }
        else{

            t="2";

        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager layoutManager1=layoutManager;
        rcyl.setLayoutManager(layoutManager1);
         listele();


        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"tıklandı",Toast.LENGTH_LONG).show();
                //giriş yapılmış olamalı
                if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){
              durumdegistir(id,"0",SharedPreferencesDurum.getMyStringPref(getApplicationContext()));
                }
                else{
                    Toast.makeText(getApplicationContext(),"giriş yapmalısın",Toast.LENGTH_LONG).show();

                }

            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //giriş yapılmış olamalı
               // Toast.makeText(getApplicationContext(),"tıklandı",Toast.LENGTH_LONG).show();
                if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){
                durumdegistir(id,"1",SharedPreferencesDurum.getMyStringPref(getApplicationContext()));
                }else{
                    Toast.makeText(getApplicationContext(),"giriş yapmalısın",Toast.LENGTH_LONG).show();

                }
            }
        });
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!comment.getText().toString().isEmpty()){
                    data.clear();
                    StringRequest stringRequest2=new StringRequest(Request.Method.POST, url_addcomment,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        if(code.equals("eklendi")) {

                                            Toast.makeText(getApplicationContext(),"eklendi",Toast.LENGTH_LONG).show();
                                            listele();
                                            comment.setText("");
                                        }
                                        else{

                                           Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                                        }


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
                            params.put("post_id",id);
                            params.put("comment_icerik",comment.getText().toString());
                            params.put("comment_gonderen",SharedPreferencesDurum.getMyStringPref(getApplicationContext()));


                            return params;
                        }
                    };
                    MyVolley.getInstance(getApplicationContext()).addToRequestque(stringRequest2);


                }
                else{

                    Toast.makeText(getApplicationContext(),"boş mesaj atma",Toast.LENGTH_LONG).show();



                }

            }
        });

    }
    public void durumdegistir(final String post_id, final String durum1, final String gonderen){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_durum,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject;

                                jsonObject=jsonArray.getJSONObject(0);
                            like.setText(jsonObject.getString("like"));
                            dis.setText(jsonObject.getString("dis"));
                            if(t==durum1){
                                dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightdislike2, 0);
                                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightlike2, 0);
                               // t="2";
                            }
                            else if(t=="1" && durum1=="0"){
                                //like dan dise
                                dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darkdislike1, 0);
                                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightlike2, 0);
                                t="0";
                            }
                            else if(t=="0" && durum1=="1"){

                                //disten lkike
                                dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightdislike2, 0);
                                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darklike1, 0);
                                t="1";

                            }
                            else {

                                if(durum1=="1"){

                                    dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightdislike2, 0);
                                    like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darklike1, 0);
                                }
                                else{

                                    dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darkdislike1, 0);
                                    like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lightlike2, 0);
                                }



                            }

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
                params.put("post_id",post_id);
                params.put("durum",durum1);
                params.put("durum_kisi",gonderen);


                return params;
            }
        };
        MyVolley.getInstance(getApplicationContext()).addToRequestque(stringRequest);

    }
    public void listele(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_comment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject;
                            for(int i=0;i<jsonArray.length();i++){

                                jsonObject=jsonArray.getJSONObject(i);

                                data.add(new CommentModel(jsonObject.getString("com_gonderen"),jsonObject.getString("com_icerik"),jsonObject.getString("com_id"),jsonObject.getString("com_tarih")));
                            }

                            adapter=new CommentAdapter(getApplicationContext(),data);
                            rcyl.setAdapter(adapter);

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
                params.put("post_id",id);


                return params;
            }
        };
        MyVolley.getInstance(getApplicationContext()).addToRequestque(stringRequest);





    }
}
