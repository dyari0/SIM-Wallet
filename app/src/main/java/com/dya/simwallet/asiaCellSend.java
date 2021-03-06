package com.dya.simwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class asiaCellSend extends AppCompatActivity {
    EditText bry_para , Phon_number;
    Button btnNardn,btnZanyn;
    BottomNavigationView bottom_navigation;
    ImageButton editSim;

    int s=1;
    String sim="asi";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asiacell_send);
        if (Build.VERSION.SDK_INT>+21){
            //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
            Window window =this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.asiaCell));

        }

        btnNardn = (Button)findViewById(R.id.daxlAsia);
        btnZanyn = (Button)findViewById(R.id.zaninAsia);
        Phon_number = (EditText)findViewById(R.id.Phon_number);
        bry_para = (EditText)findViewById(R.id.bry_para);
        editSim =(ImageButton)findViewById(R.id.editSim);
        editSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(asiaCellSend.this,select.class);
                startActivity(intent);
                finish();
            }
        });

        bottom_navigation = findViewById(R.id.nav);
        if (s==1){
            bottom_navigation.setSelectedItemId(R.id.send);
        }

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.developer){
                     Intent intent = new Intent(asiaCellSend.this,Developer.class);
                    intent.putExtra("sim",sim);
                     startActivity(intent);
                    finish();

                }
                else  if (id==R.id.wallet){
                    Intent intent = new Intent(asiaCellSend.this,AsiaCell.class);
                    startActivity(intent);
                    finish();
                }
                else  if (id==R.id.send){
                    Toast.makeText(asiaCellSend.this,"it is SendPage", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        btnZanyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                String num ="*133#";
                intent.setData(Uri.parse(String.format("tel:%s",Uri.encode(num))));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(asiaCellSend.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intent);
                }
            }
        });


        btnNardn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                String num ="*123*";
                String KNum=bry_para.getText().toString();
                String PNum="*"+Phon_number.getText().toString()+"#";
                intent.setData(Uri.parse(String.format("tel:%s",Uri.encode(num+KNum+PNum))));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(asiaCellSend.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intent);
                    bry_para.setText("");
                    Phon_number.setText("");
                }
            }
        });




    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(asiaCellSend.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}