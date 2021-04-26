package com.example.asim.darewrofyp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asim on 5/15/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    Context context;
   String[] rn;
    public CustomAdapter(Context context, String[] resource) {
        super(context, Integer.parseInt(resource.toString()));//???????????????????????????????????????????



            this.context = context;
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.customlayout,null);
            TextView textView = (TextView) view.findViewById(R.id.tvcustom1);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linLayout);

            textView.setText(rn[position]);

            return view;
    }
}
