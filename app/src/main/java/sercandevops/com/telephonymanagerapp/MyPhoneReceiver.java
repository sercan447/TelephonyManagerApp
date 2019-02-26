package sercandevops.com.telephonymanagerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, final Intent intent) {


        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener() {

            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);

                Log.i("HEDEF", "Numara : " + phoneNumber);


                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);

                //phoneNumber = phoneNumber.substring(phoneNumber.indexOf(1,phoneNumber.length()),phoneNumber.length());

                Intent intentToMain = new Intent("my.result.receiver");
                intentToMain.putExtra("incomingNumber", phoneNumber);

                manager.sendBroadcast(intentToMain);

                context.startService(new Intent(context.getApplicationContext(),Servisim.class));

            }
        }, PhoneStateListener.LISTEN_CALL_STATE);


    }
}
