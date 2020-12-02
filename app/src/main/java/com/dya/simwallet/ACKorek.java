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

public class ACKorek extends AppCompatActivity {

    EditText edCode;
    Button btnDaxlkrdn,btnZanyn;
    BottomNavigationView bottom_navigation;
    int s=1;
    ImageButton editSim;
    String sim="korek";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c_korek);
        if (Build.VERSION.SDK_INT>+21){
            //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
            Window window =this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Korek));

        }

        btnDaxlkrdn = (Button)findViewById(R.id.daxlKorek);
        btnZanyn = (Button)findViewById(R.id.zaninKorek);
        edCode = (EditText)findViewById(R.id.Phon_number);
        editSim =(ImageButton)findViewById(R.id.editSim);
        editSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ACKorek.this,select.class);
                startActivity(intent);
                finish();

            }
        });
        bottom_navigation = findViewById(R.id.nav);
        if (s==1){
            bottom_navigation.setSelectedItemId(R.id.wallet);
        }

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.developer){
                   Intent intent = new Intent(ACKorek.this,Developer.class);
                    intent.putExtra("sim",sim);
                    startActivity(intent);
                    finish();
                }
                else  if (id==R.id.wallet){
                    Toast.makeText(ACKorek.this, "it is WalletPage", Toast.LENGTH_SHORT).show();
                }
                else  if (id==R.id.send){
                    Intent intent = new Intent(ACKorek.this,korekSend.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

        btnZanyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                String num ="*211#";
                intent.setData(Uri.parse(String.format("tel:%s",Uri.encode(num))));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(ACKorek.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intent);
                }
            }
        });

        btnDaxlkrdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                String num ="*211*";
                String codNum=edCode.getText().toString();
                String Hash="#";
                intent.setData(Uri.parse(String.format("tel:%s",Uri.encode(num+codNum+Hash))));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(ACKorek.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intent);
                    edCode.setText("");
                }
            }
        });
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(ACKorek.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}