package com.tasarm.proje.prjtsrm.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.classs.MyVolley;
import com.tasarm.proje.prjtsrm.classs.Query;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    Button kayıt,giris;
    EditText sifre,kllanici;
    Query query=new Query();
    Context context;
    String url_login;//http://10.0.2.2/android/register_query.php
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        kayıt=(Button)findViewById(R.id.kayıt);
        giris=(Button)findViewById(R.id.giris);
        sifre=(EditText)findViewById(R.id.sifre);
        kllanici=(EditText)findViewById(R.id.kullanıcı);
        context=getApplicationContext();
        String base=getString(R.string.adres);
        url_login=base+"/android/register_query.php";
        kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register_Screen.class));
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sifre.getText().toString().isEmpty() && !kllanici.getText().toString().isEmpty()){//boş olup olmadığına bakar
                if(query.sifreler(Integer.parseInt(sifre.getText().toString()))==false){//şifre altı hanelimi
                    Toast.makeText(getApplicationContext(),"şifre 6 haneli olmalı",Toast.LENGTH_LONG).show();

                }
                else{

                    //kullanıcı varmı yokmu bak ve giriş yap
                    //çalışma sırası yanlış ilk metodu çağırıyor ancak medodun içini sonradan çalıştırıyor bu yüzden metod yanlş sonuç dönderiyor
                  //  boolean bool= databaseContact.registerquery(kllanici.getText().toString(),sifre.getText().toString(),context);


                            StringRequest stringRequest=new StringRequest(Request.Method.POST, url_login,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray jsonArray=new JSONArray(response);
                                                JSONObject jsonObject=jsonArray.getJSONObject(0);
                                                String code=jsonObject.getString("code");
                                                String mesaj=jsonObject.getString("message");
                                                if(code.equals("dogru")){


                                                    SharedPreferencesDurum.setMyBoolPref(getApplicationContext(),true);
                                                    startActivity(new Intent(getApplicationContext(),AnaSayfa.class));
                                                    SharedPreferencesDurum.setMyStringPref(getApplicationContext(),kllanici.getText().toString());
                                                    Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show();

                                                }
                                                else{
                                                    Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show();

                                                }


                                            } catch (JSONException e) {
                                                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
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
                                    params.put("kullanıcı_adi",kllanici.getText().toString());
                                    params.put("sifre",sifre.getText().toString());
                                    return params;
                                }
                            };
                            MyVolley.getInstance(context).addToRequestque(stringRequest);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"tam doldur",Toast.LENGTH_LONG).show();
                             }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
