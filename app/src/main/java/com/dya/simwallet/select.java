package com.dya.simwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

public class select extends AppCompatActivity {

    RadioButton radioButton1,radioButton2;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            Toast.makeText(select.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
            requestPermission();
        }
        if (Build.VERSION.SDK_INT>+21){
            //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
            Window window =this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.dark));

        }

        radioButton1 = (RadioButton) findViewById(R.id.radioBtn1);
        radioButton2 = (RadioButton) findViewById(R.id.radioBtn2);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton2.setChecked(false);
                radioButton1.setChecked(true);
                Intent intent = new Intent(select.this,AsiaCell.class);
                startActivity(intent);
                finish();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(true);
                Intent intent = new Intent(select.this, ACKorek.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(select.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}