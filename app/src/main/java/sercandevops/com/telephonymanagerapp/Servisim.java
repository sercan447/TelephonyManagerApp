package sercandevops.com.telephonymanagerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class Servisim extends Service {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent intent2 = new Intent(Servisim.this,MainActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent2);

        return super.onStartCommand(intent, flags, startId);

    }
}
