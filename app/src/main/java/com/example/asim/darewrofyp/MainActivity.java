package com.example.asim.darewrofyp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

        EditText email, password;
        String Email, Password;
        Context ctx=this;
        String NAME=null, EMAIL=null, PASSWORD=null,CONTACT=null,Address=null;
    MySqliteHelper mySqliteHelper;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            email = (EditText) findViewById(R.id.etemail);
            password = (EditText) findViewById(R.id.etpass);

            ///////////////////////////////////////////////////////////

            mySqliteHelper = new MySqliteHelper(this);
            File dbFile = getApplicationContext().getDatabasePath(MySqliteHelper.DB_NAME);
            if (false == dbFile.exists()) {
                mySqliteHelper.getReadableDatabase();

                if (copyDataBase(this)) {
                    Toast.makeText(getBaseContext(), "database succefully copied", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "database  copied error", Toast.LENGTH_SHORT).show();
                }
            }


        }

        public void reg(View v){
            startActivity(new Intent(this,Main2Activity.class));
        }

        public void Onlogin(View v){
            Email = email.getText().toString();
            Password = password.getText().toString();
            BackGround b = new BackGround();
            b.execute(Email, Password);
        }

        class BackGround extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... params) {
                String Email = params[0];
                String Password = params[1];
                String data="";
                int tmp;

                try {
                    URL url = new URL("http://192.168.1.22/Darewro_Project/login.php");
                    String urlParams = "Email="+Email+"&Password="+Password;

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
                String err=null;
                try {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data = root.getJSONObject("user_data");
                    NAME = user_data.getString("Name");
                    EMAIL = user_data.getString("Email");
                    PASSWORD = user_data.getString("Password");
                    CONTACT = user_data.getString("Contact");
                    Address=user_data.getString("Address");
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: "+e.getMessage();
                }

                Intent i = new Intent(ctx, home.class);
                i.putExtra("Name", NAME);
                i.putExtra("Email", EMAIL);
                i.putExtra("Password", PASSWORD);
                i.putExtra("Contact", CONTACT);
                i.putExtra("Address", Address);
                i.putExtra("err", err);
                startActivity(i);

            }
        }


    public boolean copyDataBase(Context context)
    {
        try
        {
            InputStream inputStream = context.getAssets().open(mySqliteHelper.DB_NAME);
            String outFileName = MySqliteHelper.DB_LOCATION + MySqliteHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buff)) > 0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.d("copying database", "successfully copied");
            return  true;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
