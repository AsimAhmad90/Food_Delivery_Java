package com.example.asim.darewrofyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class home extends AppCompatActivity {
    String Name, Password, Email,Address,Contact, Err;
    TextView nameTV, emailTV, passwordTV,addresstv,contactTV, err;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b2 = (Button) findViewById(R.id.tracker);

        b1 = (Button) findViewById(R.id.main_menu);
        nameTV = (TextView) findViewById(R.id.textView);
        emailTV = (TextView) findViewById(R.id.textView2);
        passwordTV = (TextView) findViewById(R.id.textView3);
        contactTV = (TextView) findViewById(R.id.textView4);
        addresstv=(TextView) findViewById(R.id.textView5) ;
        err = (TextView) findViewById(R.id.textView6);

        Name = getIntent().getStringExtra("Name");
        Password = getIntent().getStringExtra("Password");
        Email= getIntent().getStringExtra("Email");
        Contact = getIntent().getStringExtra("Contact");
        Address = getIntent().getStringExtra("Address");
        Err = getIntent().getStringExtra("err");

        nameTV.setText("Welcome "+Name);
        passwordTV.setText("Your password is "+Password);
        emailTV.setText("Your email is "+Email);
        addresstv.setText("Your address is "+Address);
        contactTV.setText("Your contact is "+Contact);
        err.setText(Err);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(home.this,Main_Menu.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }

}

