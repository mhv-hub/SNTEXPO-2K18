package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Home extends Fragment{

    View myView;
    Button regbut;

    int ind=0;
    int len=0;
    String json_array;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ProgressDialog p;

    Button retry;

    RelativeLayout temp;

    Bitmap bmp;

    String event_name[] = new String[100];
    String reg_charge[] = new String[100];
    String venue[]  = new String[100];
    String event_date[]  = new String[100];
    String time[]  =new String[100];
    String last_date[]  = new String[100];
    String rules[]  = new String[100];
    String fac_cord_all[]  = new String[100];
    String stud_cord_all[]  = new String[100];
    String type[]  = new String[100];
    String event_type[] = new String[100];



    ListView listView;
    List<HashMap<String,String>> obj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_home, container, false);

        regbut = (Button) myView.findViewById(R.id.regbut);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/head.otf");
        regbut.setTypeface(type);

        listView = (ListView) myView.findViewById(R.id.event_list);
        temp = (RelativeLayout) myView.findViewById(R.id.temp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ievent_name,ifac_cord,istud_cord,irules;
                ievent_name=event_name[position];
                ifac_cord=fac_cord_all[position];
                istud_cord=stud_cord_all[position];
                irules=rules[position];



                Intent i = new Intent(getActivity(),EventDetailsActivity.class);
                i.putExtra("event_name",ievent_name);
                i.putExtra("rules",irules);
                i.putExtra("fac_cord",ifac_cord);
                i.putExtra("stud_cord",istud_cord);
                startActivity(i);

            }
        });

        retry=(Button) myView.findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                temp.setVisibility(View.GONE);
                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute();

            }
        });

        regbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        initialize();
        return myView;

    }


    public void setDetails(){



        obj = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<ind;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("event_name",event_name[i]);
            hm.put("event_date",event_date[i]);
            hm.put("last_date","Last reg. date : "+last_date[i]);
            hm.put("venue", "Venue : "+venue[i]);
            hm.put("time", time[i]);
            hm.put("charge","Reg. Charge : "+reg_charge[i]);
            hm.put("type",event_type[i]);
            obj.add(hm);

            String[] from = {"event_name","event_date","last_date","venue","time","charge","type" };

            int[] to = {R.id.event_name,R.id.event_date,R.id.last_date,R.id.venue,R.id.time,R.id.charge,R.id.type};
            SimpleAdapter adapter = new SimpleAdapter(myView.getContext(), obj, R.layout.list_one, from, to);
            listView.setAdapter(adapter);
        }

        sendToMain();

    }

    public void sendToMain(){
        ((MainActivity)getActivity()).setDetails(event_name,rules,fac_cord_all,stud_cord_all,type,ind);
    }

    public void initialize(){
        if(!isNetworkAvailable()){
            listView.setVisibility(View.GONE);
            temp.setVisibility(View.VISIBLE);
        }
        else{
            BackgroundTask backgroundTask = new BackgroundTask();
            backgroundTask.execute();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }




    class BackgroundTask extends AsyncTask<Void , Void , String> {

        String json_url;
        String JSON_STRING="";

        @Override
        protected void onPreExecute() {
            p  = new ProgressDialog(getActivity());
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setMessage("Loading...Please wait");
            p.setIndeterminate(true);
            p.setCancelable(false);
            p.show();
            json_url = "https://sntexpo2k18.000webhostapp.com/ReadEventDetails.php";
        }

        @Override
        protected String doInBackground(Void... voids) {



            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        p.cancel();
                        p.hide();
                        listView.setVisibility(View.GONE);
                        temp.setVisibility(View.VISIBLE);
                    }
                });
            }

            return "Failed";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_array = result;
            try {
                jsonObject = new JSONObject(json_array);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count =0;
                len = jsonArray.length();
                ind=0;
                while(count<jsonArray.length()){
                    JSONObject jo = jsonArray.getJSONObject(count);
                    event_name[ind] = jo.getString("event_name");
                    event_name[ind] = event_name[ind].substring(0, 1).toUpperCase() + event_name[ind].substring(1);
                    event_date[ind] = jo.getString("event_date");
                    last_date[ind] = jo.getString("last_date");
                    time[ind] = jo.getString("event_time");
                    venue[ind] = jo.getString("venue");
                    reg_charge[ind] = jo.getString("charge");
                    rules[ind]=jo.getString("rules");
                    fac_cord_all[ind]= jo.getString("fac_cord_all");
                    stud_cord_all[ind] = jo.getString("stud_cord_all");
                    type[ind] = jo.getString("type");


                    if(type[ind].equals("nt"))
                        event_type[ind] = "Non-Technical";
                    else if(type[ind].equals("t"))
                        event_type[ind] = "Technical";
                    else
                        event_type[ind] = "Art & Craft";

                    ind++;
                    count++;
                }
                p.cancel();
                p.hide();
                setDetails();
            } catch (JSONException e) {

                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            p.cancel();
                            p.hide();
                            listView.setVisibility(View.GONE);
                            temp.setVisibility(View.VISIBLE);
                        }
                    });
            }


        }
    }




}
