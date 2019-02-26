package sercandevops.com.telephonymanagerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView callortext;
    LocalBroadcastManager manager;

    private String caller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      callortext = (TextView)findViewById(R.id.collorText);

        manager = LocalBroadcastManager.getInstance(this);

        caller = "";

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String gelenCagri = intent.getStringExtra("incomingNumber");

            //Toast.makeText(context,gelenCagri,Toast.LENGTH_SHORT).show();
                matchContact(gelenCagri);

        }
    };

    public void matchContact(String number)
    {


        ArrayList<String> nameFromDB  = new ArrayList<String>();
        ArrayList<String> numbersFromDB = new ArrayList<String>();

        HashMap<String,String> contactInfo = new HashMap<String,String>();

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("sercandevops.com.telephonymanagerapp",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS phonebook (name VARCHAR,number VARCHAR)");
        sqLiteDatabase.execSQL("INSERT INTO phonebook (name,number) VALUES ('JAMES', '+905635456545')");
        sqLiteDatabase.execSQL("INSERT INTO phonebook (name,number) VALUES ('sultanus','+905413790642')");
        sqLiteDatabase.execSQL("INSERT INTO phonebook (name,number) VALUES ('sercanus', '+905385775558')");
        sqLiteDatabase.execSQL("INSERT INTO phonebook (name,number) VALUES ('evus', '02124295728')");

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  phonebook",null);

        int nameIx = cursor.getColumnIndex("name");
        int numberIx = cursor.getColumnIndex("number");

        while(cursor.moveToNext())
        {
            String namefrom = cursor.getString(nameIx);
            String numberform = cursor.getString(numberIx);

            nameFromDB.add(namefrom);
            numbersFromDB.add(numberform);

            contactInfo.put(numberform,namefrom);

        }//while

        cursor.close();

        for(String s : numbersFromDB)
        {
            if(s.equals(number))
            {
                //contact name var
                caller = contactInfo.get(number);
                callortext.setText("Arayan Kisi : " +caller);

                Toast.makeText(getApplicationContext(), caller, Toast.LENGTH_SHORT).show();

            }else
            {
                //contact name yok
                caller= "";

            }
            caller = "";
        }

    }//FUNC

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("my.result.receiver");

         manager.registerReceiver(receiver,filter);



        Log.i("TAGLAN","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

       // manager.unregisterReceiver(receiver);
        Log.i("TAGLAN","onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAGLAN","onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAGLAN","onStop");

       onStart();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAGLAN","onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAGLAN","onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("TAGLAN","onPostResume");
    }


}
