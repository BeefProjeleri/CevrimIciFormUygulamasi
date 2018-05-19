package com.tasarm.proje.prjtsrm.activitys;

import android.content.Context;
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
import com.tasarm.proje.prjtsrm.classs.MyVolley;
import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {

    EditText konu,icerik;
    Button kaydet;
    Context context;
    String url_post;//http://10.0.2.2/android/post_add.php
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        kaydet=(Button)findViewById(R.id.ekle);
        konu=(EditText)findViewById(R.id.editText);
        icerik=(EditText)findViewById(R.id.editText2);
        context=getApplicationContext();

        String base=getString(R.string.adres);
        url_post=base+"/android/post_add.php";

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!konu.getText().toString().isEmpty() || !icerik.getText().toString().isEmpty()){


                StringRequest stringRequest=new StringRequest(Request.Method.POST, url_post,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String mesaj=jsonObject.getString("message");

                                    if(code.equals("eklendi")){

                                        Toast.makeText(context,"postunuz eklendi",Toast.LENGTH_LONG).show();
                                        konu.setText("");
                                        icerik.setText("");
                                        onBackPressed();

                                    }
                                    else{
                                        Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show();
                                    }


                                } catch (JSONException e) {
                                    Toast.makeText(context,"hatarequest::."+e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"hatavolley:::."+error.toString(),Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("post_konu",konu.getText().toString());
                        params.put("post_icerik",icerik.getText().toString());
                        params.put("post_gonderen", SharedPreferencesDurum.getMyStringPref(context));

                        return params;
                    }
                };
                MyVolley.getInstance(getApplicationContext()).addToRequestque(stringRequest);



            }
            else{

                        Toast.makeText(getApplicationContext(),"tam doldur",Toast.LENGTH_LONG).show();


                }
            }

        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
