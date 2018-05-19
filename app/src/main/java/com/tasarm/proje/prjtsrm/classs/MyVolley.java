package com.tasarm.proje.prjtsrm.classs;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by kurt on 19.01.2018.
 */

public class MyVolley {

    //bu class volley kütüphanesinin kontrollü bir şekilde çalışmasını sağlıyor
    private static MyVolley mInstance;
    private RequestQueue requestQueue;
    private Context context;
    private MyVolley(Context context){
        this.context=context;
        requestQueue=getRequestQueue();

    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context);

        return requestQueue;
    }
    public  static synchronized MyVolley getInstance(Context context){
        if(mInstance==null)
            mInstance=new MyVolley(context);

        return mInstance;
    }
    public  <T> void addToRequestque(Request<T> request){
        requestQueue.add(request);
    }

}
