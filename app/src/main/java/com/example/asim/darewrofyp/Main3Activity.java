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

public class Main3Activity extends AppCompatActivity {

    ListView lv3;
    MySqliteHelper mySqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mySqliteHelper = new MySqliteHelper(this);
        lv3 = (ListView) findViewById(R.id.lv3);

        Intent i = getIntent();
        String a= i.getStringExtra("A");

        // Toast.makeText(this, a+"", Toast.LENGTH_SHORT).show();



        final String[] list = mySqliteHelper.Selected_Rest_Item_ID(a);
        for (int ii =0;ii<list.length;ii++)
        {
            Toast.makeText(this,list[ii]+ "", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this,list.length+ "", Toast.LENGTH_SHORT).show();
        ArrayAdapter adaptor = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv3.setAdapter(adaptor);



        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Main3Activity.this,Main4Activity.class);
                i.putExtra("key",list[position]);
                startActivity(i);

            }
        });






    }
}
