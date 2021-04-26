package com.example.asim.darewrofyp;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by asim on 5/11/2017.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DarewroDB.sqlite";
    public static final String DB_LOCATION = "/data/data/com.example.asim.darewrofyp/databases/";

//String[] MainMenu ;
    // public static final String TB_NAME ="products";

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public MySqliteHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
    }

    //////////////////////OPEN DATABASE/////////////////////////////////////////////
    public void openDataBase() {
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {

            return;

        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    ///////////////////////////CLOSE DATABASE//////////////////////////////////////////////////////
    public void closeDataBase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String[]> restnames() {

        SQLiteDatabase db = this.getReadableDatabase();
        openDataBase();
        Cursor cursor = db.rawQuery(" SELECT *FROM MainMenu ", null);
        String[] R_names = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {

            R_names[i] = cursor.getString(1);
            i++;

        }

        ArrayList<String[]> list = new ArrayList<>();
        list.add(R_names);
        return list;
    }

    public String[] Selected_Rest_Item_ID(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        openDataBase();
        String[] mnames = null;
        Cursor cursor = db.rawQuery(" Select mm_ID from MainMenu Where Name_of_restaurants  =? ", new String[]{name});
        Toast.makeText(mContext, cursor.getCount() + "", Toast.LENGTH_SHORT).show();
        int m_id = 0;
        if (cursor.moveToNext())
        {
            m_id = cursor.getInt(0);
            Cursor c =db.rawQuery(" SELECT Menu FROM Chief WHERE mm_ID =? ",new String[]{String.valueOf(m_id)});
             mnames = new String[c.getCount()];
            int i =0;
            while (c.moveToNext())
            {
                mnames[i]= c.getString(0);
                i++;
            }
        }
       return mnames;

    }






    public  ArrayList<String[]> getSubManues(String tbname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        openDataBase();
        String[] tbnames=null;
        Cursor cursor = db.rawQuery(" Select * from Chief Where Menu  =? ", new String[]{tbname});
        int mid = 0;
        ArrayList<String[]> list = new ArrayList<>();

        if (cursor.moveToNext())
        {
            mid = cursor.getInt(0);
            if (mid>0)
            {
                Cursor c =db.rawQuery(" SELECT  *FROM Burger WHERE Selected_rest_Item_ID =? ",new String[]{String.valueOf(mid)});
                String[] names = new String[c.getCount()];
                String[] prices = new String[c.getCount()];
                int i =0;
                while (c.moveToNext())
                {
                    names[i] = c.getString(2);
                    prices[i]= c.getString(3);
i++;
                }
                list.add(names);
                list.add(prices);

            }
        }

        return list;
    }
       // db.rawQuery(" SELECT *FROM Burger WHERE Selected_Rest_Item_ID=? ", new String[]{Selected_Rest_Item_ID()} );
    }

