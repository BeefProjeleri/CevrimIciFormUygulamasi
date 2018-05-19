package com.tasarm.proje.prjtsrm.classs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kurt on 18.01.2018.
 */

public class Query {

    public Query(){

    }

    public boolean sifreler(int sifre){
        if(sifre>100000 && sifre<999999)
            return true;
        else
            return false;


    }
    public boolean kullanıcılar(String kullanıcı){

        if(kullanıcı.toCharArray().length>9)
        return false;
        else
            return true;
    }
    public boolean epostalar(String eposta){
            char k[]=eposta.toCharArray();
        int sayac=0;
        for (int i=0;i<k.length;i++){
            if(k[i]=='@')
                sayac=sayac+1;
        }
        if(sayac==1)
            return true;
        else
            return false;
    }



}
