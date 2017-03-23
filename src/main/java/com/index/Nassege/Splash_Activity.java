package com.index.Nassege;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Activity extends AppCompatActivity {
    Boolean go;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewSplash_logo);
        Animation animS = AnimationUtils.loadAnimation(this, R.anim.scale_splashscreen_anim);
        imageView.setAnimation(animS);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent i = new Intent(Splash_Activity.this , MainActivity.class);
                    startActivity(i);
                    finish();

            }
        }, 2000);
    }

}