package com.example.user.navigationdrawer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    Button b1;
    TextView aa,bb,cc,xx,yy,zz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        b1=(Button)findViewById(R.id.explorenow);


        aa = (TextView) findViewById(R.id.aa);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        aa.setTypeface(type);

        bb = (TextView) findViewById(R.id.bb);
        Typeface type1 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        bb.setTypeface(type);

        cc = (TextView) findViewById(R.id.cc);
        Typeface type3 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        cc.setTypeface(type);

        xx = (TextView) findViewById(R.id.xx);
        Typeface type4 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        xx.setTypeface(type);

        yy = (TextView) findViewById(R.id.yy);
        Typeface type5 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        yy.setTypeface(type);

        zz = (TextView) findViewById(R.id.zz);
        Typeface type6 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        zz.setTypeface(type);

        Typeface type7 = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        b1.setTypeface(type);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InfoActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
