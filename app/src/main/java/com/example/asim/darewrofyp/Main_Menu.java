package com.example.asim.darewrofyp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Main_Menu extends AppCompatActivity {

    ListView lv;
    MySqliteHelper mySqliteHelper;
    String[] R_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        mySqliteHelper  = new MySqliteHelper(this);

        lv = (ListView) findViewById(R.id.lv_main_menu);

        final ArrayList<String[]> list = mySqliteHelper.restnames();

        R_names = list.get(0);
        String[] ccolor = list.get(1);
       CustomAdapter adaptor = new CustomAdapter(Main_Menu.this,R_names);
        // final ArrayAdapter<String> adptr = new ArrayAdapter<String>(Main_Menu.this, android.R.layout.simple_list_item_1, R_names);
        //lv.setAdapter(adptr);
     lv.setAdapter(adaptor);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (R_names[position]=="Cheif") {

                    Intent i = new Intent(Main_Menu.this, Main3Activity.class);
                    i.putExtra("A", R_names[position]);
                    startActivity(i);
                }
                else if (R_names[position]=="KFC")
                {
                    Intent i = new Intent(Main_Menu.this, Main4Activity.class);

                    startActivity(i);
                }
                else if (R_names[position]=="MC")
                {
                    Intent i = new Intent(Main_Menu.this, Main4Activity.class);

                    startActivity(i);
                }
            }
        });





    }
}
