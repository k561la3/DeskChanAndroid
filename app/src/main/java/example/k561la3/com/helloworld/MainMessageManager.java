package example.k561la3.com.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;


public class MainMessageManager extends BroadcastReceiver {
    String text = "pidor";
    int timeout = 1000;
    int drawable = R.drawable.img50;












    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.hasExtra("text")) text = intent.getStringExtra("text");
        if(intent.hasExtra("timeout"))timeout = intent.getIntExtra("timeout",1000);
        if(intent.hasExtra("skin"))drawable = intent.getIntExtra("skin",R.drawable.img50);
        //MainActivity.guiServiceInstance.say(text);
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
