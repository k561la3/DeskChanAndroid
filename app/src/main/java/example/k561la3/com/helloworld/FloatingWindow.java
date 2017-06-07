package example.k561la3.com.helloworld;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


/**
 * Created by Admin on 04.06.2017.
 */

public class FloatingWindow extends Service{
    int screenHeight = MainActivity.height;
    int screenWidth = MainActivity.width;
    private int rotation;


//-----------------------------------------------------------------------------
    private WindowManager movableTyan;
    private LinearLayout movableTyanLayout;
    private Button stop;
    public boolean CLOUD_EXISTS = false;


    //-----------------------------------------------------------------------

    private WindowManager cloudWindow, textWindow;
    private LinearLayout cloudWindowLayout, textWindowLayout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//------------------------------------------------------------------------------


    @Override
    public void onCreate() {
        super.onCreate();

//-------------------------------------------------------------------------------





        //create gui elements
        movableTyan = (WindowManager)getSystemService(WINDOW_SERVICE);
        textWindow = (WindowManager)getSystemService(WINDOW_SERVICE);
        movableTyanLayout = new LinearLayout(this);
        stop = new Button(this);


        //create params
        ViewGroup.LayoutParams buttonParams = new ViewGroup.LayoutParams(32,32);
        stop.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.close));
        stop.setLayoutParams(buttonParams);

        //create layout
        LinearLayout.LayoutParams movableTyanParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        movableTyanLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.happy));

        movableTyanLayout.setLayoutParams(movableTyanParams);
        final WindowManager.LayoutParams movableTyanLayoutParams = new WindowManager.LayoutParams(250,450,WindowManager.LayoutParams.TYPE_PHONE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        movableTyanLayoutParams.x = 0;
        movableTyanLayoutParams.y = screenHeight-450;
        movableTyanLayoutParams.gravity =  Gravity.TOP|Gravity.LEFT;
        movableTyanLayout.addView(stop);
        movableTyan.addView(movableTyanLayout,movableTyanLayoutParams);


        //-----------------------------------------------

        cloudWindow = (WindowManager) getSystemService(WINDOW_SERVICE);
        cloudWindowLayout = new LinearLayout(this);
        textWindowLayout = new LinearLayout(this);
        final TextView textView = new TextView(this);
        textView.setText("ЭТО\nТЕКСТ");
        textView.setTextColor(Color.argb(255,250,50,50));
        final LinearLayout.LayoutParams textWindowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textWindowLayout.setLayoutParams(textWindowParams);
        final WindowManager.LayoutParams textWindowLayoutParams = new WindowManager.LayoutParams(100, 100, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        textWindowLayout.addView(textView);

        //create layout
        final LinearLayout.LayoutParams cloudParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cloudWindowLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.cloud));
        cloudWindowLayout.setLayoutParams(cloudParams);


        final WindowManager.LayoutParams cloudLayoutParams = new WindowManager.LayoutParams(160, 150, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        cloudLayoutParams.x = movableTyanLayoutParams.x + 220;
        cloudLayoutParams.y = movableTyanLayoutParams.y - 50;
        cloudLayoutParams.gravity = Gravity.TOP|Gravity.LEFT;
        cloudWindow.addView(cloudWindowLayout, cloudLayoutParams);



        textWindowLayoutParams.x = cloudLayoutParams.x + 30;
        textWindowLayoutParams.y = cloudLayoutParams.y + 20;
        textWindowLayoutParams.gravity = Gravity.TOP|Gravity.LEFT;


        textWindow.addView(textWindowLayout,textWindowLayoutParams);
        CLOUD_EXISTS = true;

        //--------------------------------------------------------------------




        movableTyanLayout.setOnTouchListener(new View.OnTouchListener() {
            private WindowManager.LayoutParams newParams = movableTyanLayoutParams;
            private WindowManager.LayoutParams fieldParams = cloudLayoutParams;
            private WindowManager.LayoutParams ntext = textWindowLayoutParams;
            int x,y;
            float touchedX,touchedY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        /*if(MainActivity.rot != rotation){
                        screenHeight+=screenWidth;
                        screenWidth = screenHeight - screenWidth;
                        screenHeight = screenHeight - screenWidth;
                        rotation = ORIENTATION_LANDSCAPE;
                        }*/

                        screenHeight = MainActivity.height;
                        screenWidth = MainActivity.width;
                        x = newParams.x;
                        y = newParams.y;
                        touchedX = event.getRawX();
                        touchedY = event.getRawY();
                        if(!CLOUD_EXISTS) {
                            cloudWindow.addView(cloudWindowLayout, fieldParams);
                            textWindow.addView(textWindowLayout,ntext);
                            CLOUD_EXISTS = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newParams.x = (int)(x+(event.getRawX()-touchedX));
                        newParams.y = (int)(y+(event.getRawY()-touchedY));
                        newParams.x = (newParams.x)<0?0:newParams.x;
                        newParams.y = (newParams.y)<0?0:newParams.y;
                        newParams.x = (newParams.x)>screenWidth-250?screenWidth-250:newParams.x;
                        newParams.y = (newParams.y)>screenHeight-450?screenHeight-450:newParams.y;
                        movableTyan.updateViewLayout(movableTyanLayout,newParams);
                        fieldParams.x = newParams.x+220;
                        fieldParams.y = newParams.y-50;
                        cloudWindow.updateViewLayout(cloudWindowLayout,fieldParams);
                        ntext.x = fieldParams.x + 30;
                        ntext.y = fieldParams.y + 20;
                        textWindow.updateViewLayout(textWindowLayout,ntext);
                        break;
                }
                return false;
            }
        });

//----------------------------------------------------------------

        cloudWindowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    textWindow.removeView(textWindowLayout);
                    cloudWindow.removeView(cloudWindowLayout);
                    CLOUD_EXISTS = false;
            }
        });


        textWindowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    textWindow.removeView(textWindowLayout);
                    cloudWindow.removeView(cloudWindowLayout);
                    CLOUD_EXISTS = false;
            }
        });
//-------------------------------------------------------------------------

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(CLOUD_EXISTS){
                    textWindow.removeView(textWindowLayout);
                    cloudWindow.removeView(cloudWindowLayout);
                    CLOUD_EXISTS = false;
                }
                movableTyan.removeView(movableTyanLayout);
                stopSelf();
            }
        });

//---------------------------------------------------------------------------

    }



    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics metrics = new DisplayMetrics();
        movableTyan.getDefaultDisplay().getMetrics(metrics);
        MainActivity.rot = this.getResources().getConfiguration().orientation;
        MainActivity.height = metrics.heightPixels;
        MainActivity.width = metrics.widthPixels;
    }

//---------------------------------------------------------------------------
    public void setImage(Drawable drawable){

    }

    public void showText(CharSequence text){

    }

    public void setScale(float scale){

    }

    public void setOpacity(float opacity){

    }





}
