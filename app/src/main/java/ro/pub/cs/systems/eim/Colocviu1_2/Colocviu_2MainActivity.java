package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu_2MainActivity extends AppCompatActivity {
    EditText edit_text_next_term;
    EditText edit_text_all_terms;
    Button add_button, compute_button;
    private static final String TAG = "Colocviu_2MainActivity";
    private BroadcastReceiver sumBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        edit_text_next_term = findViewById(R.id.Next_term);
        edit_text_all_terms = findViewById(R.id.all_terms);
        add_button = findViewById(R.id.add_button);
        compute_button = findViewById(R.id.compute_button);

        add_button.setOnClickListener(new ButtonClickListener());
        compute_button.setOnClickListener(new ButtonClickListener());
        sumBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Procesăm mesajul difuzat
                if (intent != null && intent.hasExtra(Constants.BROADCAST_RECEIVER_EXTRA)) {
                    String message = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA);
                    // Afișează mesajul într-o fereastră Toast
                    Toast.makeText(Colocviu_2MainActivity.this, "Mesaj primit: " + message, Toast.LENGTH_LONG).show();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(Constants.SUM_BROADCAST_ACTION);
        registerReceiver(sumBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int sumResult = data.getIntExtra("SUM_RESULT", 0);
            edit_text_all_terms.setText(String.valueOf(sumResult));
            if (sumResult > 10) {
                Intent serviceIntent = new Intent(this, Colocviu1_2Service.class);
                serviceIntent.putExtra(Constants.SUM_RESULT, sumResult);
                startService(serviceIntent);
            }

        }
    }

    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.add_button) {
                String all_terms = edit_text_all_terms.getText().toString();
                String next_term = edit_text_next_term.getText().toString();

                if (!next_term.isEmpty()) {
                    if (all_terms.isEmpty()) {
                        all_terms = next_term;
                    } else {
                        all_terms += "+" + next_term;
                    }
                    edit_text_all_terms.setText(all_terms);
                    edit_text_next_term.setText("");
                }
            } else if (view.getId() == R.id.compute_button) {
                Log.d(TAG, "Starting Colocviu1_2SecondaryActivity");
                Intent intent = new Intent(getApplicationContext(), Colocviu1_2SecondaryActivity.class);
                intent.putExtra(Constants.ALL_TERMS, edit_text_all_terms.getText().toString());
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.ALL_TERMS, edit_text_all_terms.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.ALL_TERMS)) {
            edit_text_all_terms.setText(savedInstanceState.getString(Constants.ALL_TERMS));
        }
    }
    @Override
    protected void onDestroy() {
        if (sumBroadcastReceiver != null) {
            unregisterReceiver(sumBroadcastReceiver);
        }
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }
}
