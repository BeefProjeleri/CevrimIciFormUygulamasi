package com.tasarm.proje.prjtsrm.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tasarm.proje.prjtsrm.R;
import com.tasarm.proje.prjtsrm.classs.SharedPreferencesDurum;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

       //Query query=new Query(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
       // ConnectivityManager cm =
         //       (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

      //  NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        //final boolean isConnected = activeNetwork != null &&
          //      activeNetwork.isConnectedOrConnecting();

        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(true){//indernettin var olup olmadığının kontrolü yapılacak
                              //  if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){//query.sharedDurum()==true      sharedPreferencesDurum.getGiris_durumu()
                                    //ana sayfaya yönlendir
                                   startActivity(new Intent(getApplicationContext(),AnaSayfa.class));
                                  //  SharedPreferencesDurum.setMyStringPref(getApplicationContext(),"giriş yapılmadı");
                                if(SharedPreferencesDurum.getMyBoolPref(getApplicationContext())){



                                }
                                else
                                {
                                    SharedPreferencesDurum.setMyStringPref(getApplicationContext(),"giriş yapılmadı");

                                }

                               // }else{

                                 //   startActivity(new Intent(getApplicationContext(),Login.class));

                              //  }

                            }else{

                                Toast.makeText(getApplicationContext(),"İNTERNET BAĞLANTINIZI KONTROL EDİN....",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
        thread.start();

    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}
