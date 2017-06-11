package example.k561la3.com.helloworld;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.content.ContentValues.TAG;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//--------------------------------------------------------------------------------------------------
    public static final String SAY_SHIT = "com.k561la3.action.GUI_SAY";
    public static HashMap<String,HashMap<String,Object>> data;
    private Button b,c,d,e;
    public static Intent service;
    public static int width = 480,height = 800,rot;

    int screenHeight = MainActivity.height;
    int screenWidth = MainActivity.width;
    private int rotation;

    public int skin;
    public String text;
    public int timeout;



    //-----------------------------------------------------------------------------



    //-----------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final EditText tw1 = (EditText) findViewById(R.id.editText) ;
        final EditText tw2 = (EditText) findViewById(R.id.editText2) ;


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        rot = this.getResources().getConfiguration().orientation;
        height = metrics.heightPixels;
        width = metrics.widthPixels;


        c = (Button) findViewById(R.id.send_text);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FloatingWindow.TYAN_EXISTS) {

                    stopService(new Intent(MainActivity.this, FloatingWindow.class));
                    FloatingWindow.TYAN_EXISTS = false;
                }
                    //stopService(new Intent(MainActivity.this, FloatingWindow.class));
                    if (!FloatingWindow.TYAN_EXISTS) {

                        startService(service = new Intent(MainActivity.this, FloatingWindow.class)
                                .putExtra("skin", skin)
                                .putExtra("text", tw1.getText().toString())
                                .putExtra("timeout", Integer.parseInt(tw2.getText().toString())));
                        FloatingWindow.TYAN_EXISTS = true;

                    }else if(FloatingWindow.TYAN_EXISTS) {

                        stopService(new Intent(MainActivity.this, FloatingWindow.class));

                    }

            }
        });

/*
        d = (Button) findViewById(R.id.tyan_forever);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stopService(new Intent(MainActivity.this, FloatingWindow.class));
                if(!FloatingWindow.TYAN_EXISTS) {

                    startService(service = new Intent(MainActivity.this, FloatingWindow.class)
                            .putExtra("skin",R.drawable.happy)
                            .putExtra("text","fck yea")
                            .putExtra("timeout",-1));

                }
            }
        });


*/


        e = (Button) findViewById(R.id.stop_foreva);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stopService(new Intent(MainActivity.this, FloatingWindow.class));
                if(FloatingWindow.TYAN_EXISTS) {

                    stopService(new Intent(MainActivity.this, FloatingWindow.class));

                }
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = (String)parent.getItemAtPosition(position);
        switch (s){
            case "Happy":
                skin = R.drawable.happy;break;
            case "Excited":
                skin = R.drawable.excitement;break;
            case "Grin":
                skin = R.drawable.grin;break;
            case "Love":
                skin = R.drawable.love;break;
            case "Scared":
                skin = R.drawable.scared;break;
            case "Shocked":
                skin = R.drawable.shocked;break;
            case "Shy":
                skin = R.drawable.shy;break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
            skin = R.drawable.happy;
    }


//--------------------------------------------------------------------------------------------------




}
