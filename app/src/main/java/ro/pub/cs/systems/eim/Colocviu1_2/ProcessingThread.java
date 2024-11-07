package ro.pub.cs.systems.eim.Colocviu1_2;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProcessingThread extends Thread {

    private final Context context;
    private final int sum;
    private boolean isRunning = true;
    private static final String TAG = "ProcessingThread";

    public ProcessingThread(Context context, int sum) {
        this.context = context;
        this.sum = sum;
    }

    @Override
    public void run() {
        Log.d(TAG, "Thread has started!");

        while (isRunning) {
            SystemClock.sleep(2000); // Delay de 2 secunde

            sendBroadcastWithSum();
        }

        Log.d(TAG, "Thread has stopped!");
    }

    private void sendBroadcastWithSum() {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        Intent intent = new Intent();
        intent.setAction(Constants.SUM_BROADCAST_ACTION);
        intent.putExtra("SUM_RESULT", sum);
        intent.putExtra("CURRENT_TIME", currentTime);

        context.sendBroadcast(intent); // Trimite broadcast
    }

    public void stopThread() {
        isRunning = false;
    }
}
