package com.example.asim.darewrofyp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    EditText name, password, email,address,contact;
    String Name, Password, Email,Address,Contact;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = (EditText) findViewById(R.id.et1);
        email = (EditText) findViewById(R.id.et2);
        password = (EditText) findViewById(R.id.et3);
        contact = (EditText) findViewById(R.id.et4);
        address = (EditText) findViewById(R.id.et5);
    }

    public void Onreg (View v){
        Name = name.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        Contact = contact.getText().toString();
        Address = address.getText().toString();
        BackGround b = new BackGround();
        b.execute(Name,Email, Password,Contact,Address);


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Contact, null, "Welcome "+Name+ "\n Your account is created with Darewro :) ", null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }



    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String Name = params[0];
            String Email = params[1];
            String Password = params[2];
            String Contact = params[3];
            String Address = params[4];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.4/Darewro_Project/register.php");
                String urlParams = "Name="+Name+"&Email="+Email+"&Password="+Password+"&Contact="+Contact+"&Address="+Address;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }




            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

}
