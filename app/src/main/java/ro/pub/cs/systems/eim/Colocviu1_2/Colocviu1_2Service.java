package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.Colocviu1_2.Constants;
import ro.pub.cs.systems.eim.Colocviu1_2.ProcessingThread;

public class Colocviu1_2Service extends Service {

    private Handler handler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(Constants.SUM_RESULT)) {
            final int sum = intent.getIntExtra(Constants.SUM_RESULT, 0);

            if (sum > 10) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction(Constants.SUM_BROADCAST_ACTION);
                        broadcastIntent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, "Data și ora: " + System.currentTimeMillis() + " Suma: " + sum);
                        sendBroadcast(broadcastIntent);
                    }
                }, 2000);
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

