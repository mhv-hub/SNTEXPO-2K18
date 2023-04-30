package com.example.user.navigationdrawer;

import android.app.FragmentManager;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int currentIndex=0;

    String event_name[] = new String[100];
    String rules[]  = new String[100];
    String fac_cord_all[]  = new String[100];
    String stud_cord_all[]  = new String[100];
    String type[]  = new String[100];
    String t[]  = new String[100];
    String nt[]  = new String[100];
    String ac[]  = new String[100];
    int len,tlen,ntlen,aclen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Home()).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void setDetails(String oevent_name[],String orules[],String ofac_cord_all[],String ostud_cord_all[],String otype[],int size){

        event_name = oevent_name;
        rules = orules;
        fac_cord_all = ofac_cord_all;
        stud_cord_all = ostud_cord_all;
        type = otype;
        len=size;

        int tind=0,ntind=0,acind=0,i=0;

        for(i=0;i<len;i++){
            if(type[i].equals("t")){
                t[tind]=event_name[i];
            }
            else if(type[i].equals("nt")){
                nt[ntind]=event_name[i];
            }
            else{
                ac[acind]=event_name[i];
            }
        }

        tlen = tind;
        ntlen = ntind;
        aclen = acind;

    }




    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_weblink) {

        }else if (id == R.id.action_contactus) {
            Intent i = new Intent(this,Contact.class);
            startActivity(i);
        }else if (id == R.id.action_developer){
            Intent i = new Intent(this,Developer.class);
            startActivity(i);
        }
        else if (id == R.id.action_notice){
            Intent i = new Intent(this,Notice.class);
            startActivity(i);
        }else if (id == R.id.action_feedback){
            Intent i = new Intent(this,Feedback.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_home) {
            if(currentIndex!=0) {
                currentIndex=0;
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , new Home())
                        .commit();
            }
        } else if (id == R.id.nav_gallery) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Gallery())
                    .commit();
        } else if (id == R.id.nav_technical) {
            currentIndex=1;
            if(tlen!=0) {
                Intent i = new Intent(MainActivity.this, Technical.class);
                i.putExtra("event_name", t);
                i.putExtra("rules", rules);
                i.putExtra("fac_cord_all", fac_cord_all);
                i.putExtra("stud_cord_all", stud_cord_all);
                i.putExtra("len", tlen);
                startActivity(i);
            }
            else{

            }
        } else if (id == R.id.nav_nontechnical) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Non_technical())
                    .commit();

        } else if (id == R.id.nav_artcraft) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Art_craft())
                    .commit();
        }else if (id == R.id.nav_winner) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Winners())
                    .commit();
        }else if (id == R.id.nav_tshirts) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new T_shirts())
                    .commit();
        }else if (id == R.id.nav_sponser) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Sponsers())
                    .commit();
        }
        else if (id == R.id.nav_att) {
            currentIndex=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new VisitorAttraction())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

    }


}
