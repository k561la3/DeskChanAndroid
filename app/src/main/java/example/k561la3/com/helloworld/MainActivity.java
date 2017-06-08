package example.k561la3.com.helloworld;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


public class MainActivity extends AppCompatActivity {
//--------------------------------------------------------------------------------------------------
    public static final String SAY_SHIT = "com.k561la3.action.GUI_SAY";
    public static HashMap<String,HashMap<String,Object>> data;
    private Button b,c;
    public static Intent service;
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
                startService(service = new Intent(MainActivity.this,FloatingWindow.class));
            }
        });

        c = (Button) findViewById(R.id.send_text);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SAY_SHIT);
                intent.putExtra("text","shit");
                intent.putExtra("skin",R.drawable.happy);
                intent.putExtra("timeout",4000);
                sendBroadcast(intent);
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
