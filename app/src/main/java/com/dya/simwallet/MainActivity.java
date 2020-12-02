package com.dya.simwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Animation frombottom , fromtop;
    Timer timer;
    ImageView igIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        igIcon = (ImageView) findViewById(R.id.ivIcon);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.fomebottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.frometope);
        igIcon.setAnimation(frombottom);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),select.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}