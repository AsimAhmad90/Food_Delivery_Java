package com.example.asim.darewrofyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    ListView lv4;
    MySqliteHelper mySqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mySqliteHelper = new MySqliteHelper(this);
        lv4 = (ListView) findViewById(R.id.lv4);


        Intent intent = getIntent();
        String n = intent.getStringExtra("key");


        ArrayList<String[]> list=mySqliteHelper.getSubManues(n);
        String[] nam = list.get(0);
        String[] p = list.get(1);


       CustomAdapter2 adapter2 = new CustomAdapter2(this,nam,p);
        lv4.setAdapter(adapter2);

       /* lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
*/

    }
}
