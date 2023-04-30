package com.example.user.navigationdrawer;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
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

public class Notice extends AppCompatActivity {


    int ind=0;
    String json_array;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ProgressDialog p;

    String msg[] = new String[1000];

    ListView listView;
    List<HashMap<String,String>> obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();

    }

    public void setDetails(){

        listView = (ListView) findViewById(R.id.notice_list);

        obj = new ArrayList<HashMap<String,String>>();
        for(int i=ind-1;i>=0;i--){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("msg",msg[i]);
            obj.add(hm);

            String[] from = {"msg" };

            int[] to = {R.id.msg};
            SimpleAdapter adapter = new SimpleAdapter(Notice.this, obj, R.layout.notice_list_layout, from, to);
            listView.setAdapter(adapter);
        }

    }

    public void initialize(){
        if(!isNetworkAvailable()){
            Toast.makeText(this, "Make sure that you have a working internet connection", Toast.LENGTH_SHORT).show();
        }
        else{
            BackgroundTask backgroundTask = new BackgroundTask();
            backgroundTask.execute();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    class BackgroundTask extends AsyncTask<Void , Void , String> {

        String json_url;
        String JSON_STRING="";

        @Override
        protected void onPreExecute() {
            p  = new ProgressDialog(Notice.this);
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setMessage("Loading...Please wait");
            p.setIndeterminate(true);
            p.setCancelable(false);
            p.show();
            json_url = "https://sntexpo2k18.000webhostapp.com/ReadNotice.php";
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        p.cancel();
                        p.hide();
                        Toast.makeText(Notice.this, "No Network abc", Toast.LENGTH_SHORT).show();
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
                ind=0;
                while(count<jsonArray.length()){
                    JSONObject jo = jsonArray.getJSONObject(count);
                    msg[ind] = jo.getString("msg");
                    ind++;
                    count++;
                }
                p.cancel();
                p.hide();
                setDetails();
            } catch (JSONException e) {

                e.printStackTrace();
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        p.cancel();
                        p.hide();
                        Toast.makeText(Notice.this, "No network ", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }
    }

}
