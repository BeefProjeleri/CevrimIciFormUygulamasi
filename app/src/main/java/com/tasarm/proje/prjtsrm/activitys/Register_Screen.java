package com.tasarm.proje.prjtsrm.activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.classs.MyVolley;
import com.tasarm.proje.prjtsrm.classs.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Screen extends AppCompatActivity {
//EKSİKLERİ SORGULAMALAR 6 HANELİ FALAN BİDE DEĞİŞİKLİK OLARAK ÜNİVERİSTEYİ EKLEMEYE ALINMADI  VERİTABANINI TAM TASARLANDIĞINDA DEĞİŞTİRİLECEK
    EditText kllanici,sifre1,sifre2,eposta;
    Button kayıtol;
    Query query=new Query();
    String url_register="http://10.0.2.2/android/register_add.php";//http://10.0.2.2/android/register_add.php
    Spinner spn;
    Context context;

    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__screen);
        kayıtol=(Button)findViewById(R.id.kaydet);
        kllanici=(EditText)findViewById(R.id.kullanıcı);
        sifre1=(EditText) findViewById(R.id.sifre1);
        sifre2=(EditText) findViewById(R.id.sifre2);
        eposta=(EditText) findViewById(R.id.eposta);
        spn=(Spinner)findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.üniversiteler,android.R.layout.simple_spinner_item);
        spn.setAdapter(adapter);
        context=getApplicationContext();
        String base=getString(R.string.adres);
        url_register=base+"/android/register_add.php";
        kayıtol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sifre1.getText().toString().isEmpty() && !sifre2.getText().toString().isEmpty() && !eposta.getText().toString().isEmpty() && !kllanici.getText().toString().isEmpty()  ){
                if(query.sifreler(Integer.parseInt(sifre1.getText().toString()))==false ){

                    Toast.makeText(context,"şifre 6 haneli olmalı",Toast.LENGTH_LONG).show();
                    //uyarı poupup menu

                }
                else if(Integer.parseInt(sifre1.getText().toString())!=Integer.parseInt(sifre2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"şifreler aynı değil",Toast.LENGTH_LONG).show();
                    //uyarı poupup menu

                }
                else if(query.kullanıcılar(kllanici.getText().toString())==false){

                    Toast.makeText(context,"kullanıcı adı en fazla 9 haneli olmalı",Toast.LENGTH_LONG).show();
                    //uyarı
                }
                else if(query.epostalar(eposta.getText().toString())==false){
                    Toast.makeText(context,"böyle eposta olmaz",Toast.LENGTH_LONG).show();
                    //uyarı

                }
                else{
                  //  Toast.makeText(getApplicationContext(),"tamam kullanıcı adı var mı yok mu",Toast.LENGTH_LONG).show();
                   StringRequest stringRequest=new StringRequest(Request.Method.POST, url_register,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        String mesaj=jsonObject.getString("message");
                                        Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show();

                                        if(code.equals("eklendi")){

                                           Toast.makeText(context,"eklendi artık giriş yapabilirsiniz",Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                         Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show();
                                        }


                                    } catch (JSONException e) {
                                        Toast.makeText(context,"hata::."+e.toString(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,"hat:::."+error.toString(),Toast.LENGTH_LONG).show();

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("adi",kllanici.getText().toString());
                            params.put("sifre",sifre1.getText().toString());
                            params.put("eposta",eposta.getText().toString());
                          //  Toast.makeText(context,"bişi3",Toast.LENGTH_LONG).show();

                            return params;
                        }
                    };
                    MyVolley.getInstance(getApplicationContext()).addToRequestque(stringRequest);
                  // databaseContact.addregister(kllanici.getText().toString(),sifre1.getText().toString(),eposta.getText().toString(),spn.getSelectedItem().toString(),context);


                }}
                else{

                    Toast.makeText(context,"tam doldur",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
