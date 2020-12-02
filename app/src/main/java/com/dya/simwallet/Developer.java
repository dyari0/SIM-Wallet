package com.dya.simwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Developer extends AppCompatActivity {

    ImageButton fb,ig,gm,yt;
    int s=1;
    BottomNavigationView bottom_navigation;

    String sim;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        if (Build.VERSION.SDK_INT>+21){
            //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
            Window window =this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Developer));

        }

        sim=getIntent().getStringExtra("sim");

        fb = (ImageButton)findViewById(R.id.facebook);
        ig = (ImageButton)findViewById(R.id.ing);
        gm = (ImageButton)findViewById(R.id.emale);
        yt = (ImageButton)findViewById(R.id.ytb);
        bottom_navigation = findViewById(R.id.nav);

        if (s==1){
            bottom_navigation.setSelectedItemId(R.id.developer);
        }

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facbookUrl = "https://www.facebook.com/dya.000/";
                try {
                    int versinCode = getPackageManager().getPackageInfo("com.facebook.katana",0).versionCode;
                    if (versinCode>=3002850){
                        Uri uri = Uri.parse("fb://facewebmodal/f?href="+facbookUrl);
                        startActivity(new Intent(Intent.ACTION_VIEW,uri));
                    }else{
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("fb://profile/218345114850283")));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(facbookUrl)));
                }
            }
        });
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://instagram.com/_u/dya_garmiany/");
                Intent linkIg = new Intent(Intent.ACTION_VIEW,uri);
                linkIg.setPackage("com.instagram.android");

                try {
                    startActivity(linkIg);

                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/dya_garmiany/")));
                }
            }
        });
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(("https://www.youtube.com/channel/UCqOQuksZHtX0ug8qn-ZiaYw"));
                Intent linkYt = new Intent(Intent.ACTION_VIEW,uri);
                linkYt.setPackage("com.google.android.youtube");
                try {
                    startActivity(linkYt);
                }catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCqOQuksZHtX0ug8qn-ZiaYw")));
                }

            }
        });
        gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail ="dyarinasih51@gmail.com";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+mail));
                startActivity(intent);
            }
        });



        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.developer){

                    Toast.makeText(Developer.this, "it is Developer", Toast.LENGTH_SHORT).show();
                }
                else  if (id==R.id.wallet){
                    if (sim.equals("asi")){
                    Intent intent = new Intent(Developer.this,AsiaCell.class);
                    startActivity(intent);
                    finish();
                    }
                    else if (sim.equals("korek")){
                        Intent intent = new Intent(Developer.this,ACKorek.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else  if (id==R.id.send){
                    if (sim.equals("asi")){
                        Intent intent = new Intent(Developer.this,asiaCellSend.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (sim.equals("korek")){
                        Intent intent = new Intent(Developer.this,korekSend.class);
                        startActivity(intent);
                        finish();
                    }

                }
                return true;
            }
        });
    }
}