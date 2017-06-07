package example.k561la3.com.helloworld;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


public class MainActivity extends AppCompatActivity {
//--------------------------------------------------------------------------------------------------
    public static HashMap<String,HashMap<String,Object>> data;
    private Button b;
    private Intent service;
    public static int width = 480,height = 800,rot;

    //----------------------------------------------------------------------------------------------




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        rot = this.getResources().getConfiguration().orientation;
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        b = (Button) findViewById(R.id.start);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View n) {
                new Thread() {
                    public void run() {
                        startService(service = new Intent(MainActivity.this,FloatingWindow.class));
                    }
                }.start();
            }
        });








    }

    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        rot = this.getResources().getConfiguration().orientation;
        height = metrics.heightPixels;
        width = metrics.widthPixels;


    }

    //----------------------------------------------------------------------------------------------

    protected void onStart(){
        super.onStart();

        rot = this.getResources().getConfiguration().orientation;



    }



//--------------------------------------------------------------------------------------------------




}
