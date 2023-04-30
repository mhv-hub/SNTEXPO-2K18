package com.example.user.navigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Feedback extends AppCompatActivity {

    EditText name,email,fdbck;
    Button submit;
    String sname,semail,sfdbck;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feedback);

        name = ( EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        fdbck = (EditText) findViewById(R.id.fb);

        submit = (Button ) findViewById(R.id.submit);
        submit = (Button) findViewById(R.id.submit);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/head.otf");
        submit.setTypeface(type);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override





            public void onClick(View v) {
                sname = name.getText().toString();
                semail = email.getText().toString();
                sfdbck = fdbck.getText().toString();







                if(sname.length()!=0 && semail.length()!=0 && sfdbck.length()!=0)
                {
                        BackgroundTask bt = new BackgroundTask();
                        bt.execute();
                }
                else{
                    Toast t = Toast.makeText(Feedback.this, "Please fill all the details", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER,0,0);
                    t.show();
                }

            }
        });


    }

    public void uploaded(){
        name.setText("");
        email.setText("");
        fdbck.setText("");
    }

    class BackgroundTask extends AsyncTask<Void , Void , String> {

        String json_url;
        String JSON_STRING="";

        @Override
        protected void onPreExecute() {
            p  = new ProgressDialog(Feedback.this);
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setMessage("Submiting your feedback.....Please wait");
            p.setIndeterminate(true);
            p.setCancelable(false);
            p.show();
            json_url = "https://sntexpo2k18.000webhostapp.com/UploadFeedback.php";

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(sname,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(semail,"UTF-8")+"&"+
                        URLEncoder.encode("msg","UTF-8")+"="+URLEncoder.encode(sfdbck,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));


                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Feedback.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        uploaded();
                    }
                });

                p.cancel();
                p.hide();
                return null;


            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Feedback.this, "Network problem", Toast.LENGTH_SHORT).show();
                        p.cancel();
                        p.hide();
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

        }
    }

}
