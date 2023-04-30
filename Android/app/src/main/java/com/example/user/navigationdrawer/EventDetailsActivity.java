package com.example.user.navigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;

public class EventDetailsActivity extends AppCompatActivity {

    TextView rules,aaa,bbb,ccc,fac_cord,stud_cord;
    ImageView image ;
    String srules,sevent_name,sfac_cord,sstud_cord,trimname;
    Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_event_details);



        sevent_name = getIntent().getStringExtra("event_name");
        srules = getIntent().getStringExtra("rules");
        sfac_cord = getIntent().getStringExtra("fac_cord");
        sstud_cord = getIntent().getStringExtra("stud_cord");

        trimname = sevent_name.substring(0, sevent_name.indexOf(' '));



        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        image = (ImageView) findViewById(R.id.image);
        String url = "https://sntexpo2k18.000webhostapp.com/"+trimname+"2.jpg";
        Glide.with(this).load(url).into(image);


        collapsingToolbar.setTitle(sevent_name);

        rules = (TextView) findViewById(R.id.rules);
        rules.setText(srules);

        aaa = (TextView) findViewById(R.id.aaa);
        aaa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sw.ttf"));

        bbb = (TextView) findViewById(R.id.bbb);
        bbb.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sw.ttf"));

        ccc = (TextView) findViewById(R.id.ccc);
        ccc.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sw.ttf"));


        fac_cord = (TextView) findViewById(R.id.fac_cord);
        fac_cord.setText(sfac_cord);

        stud_cord = (TextView) findViewById(R.id.stud_cord);
        stud_cord.setText(sfac_cord);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.register) {

        }
        return super.onOptionsItemSelected(item);
    }


}
