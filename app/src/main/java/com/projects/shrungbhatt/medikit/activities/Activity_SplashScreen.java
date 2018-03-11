package com.projects.shrungbhatt.medikit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.projects.shrungbhatt.medikit.R;

public class Activity_SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent =
                        new Intent(Activity_SplashScreen.this,Activity_Login.class);
                Activity_SplashScreen.this.startActivity(mainIntent);
                Activity_SplashScreen.this.finish();
            }
        }, 1000);
    }

}
