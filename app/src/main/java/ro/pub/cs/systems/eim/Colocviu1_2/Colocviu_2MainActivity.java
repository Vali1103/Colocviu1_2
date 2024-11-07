package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu_2MainActivity extends AppCompatActivity {
    EditText edit_text_next_term;
    EditText edit_text_all_terms;
    Button add_button, compute_button;
    private static final String TAG = "Colocviu1_2SecondaryActivity";


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
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.ALL_TERMS)) {
            String allTerms = intent.getStringExtra(Constants.ALL_TERMS);
            String[] termsArray = allTerms.split("\\+");

            int sum = 0;
            for (String term : termsArray) {
                sum += Integer.parseInt(term);
            }

            // Printează suma în log
            Log.d(TAG, "Suma calculată este: " + sum);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("SUM_RESULT", sum);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int sumResult = data.getIntExtra("SUM_RESULT", 0);
            edit_text_all_terms.setText(String.valueOf(sumResult));
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
                Intent intent = new Intent(getApplicationContext(), Colocviu_2MainActivity.class);
                intent.putExtra(Constants.ALL_TERMS, edit_text_all_terms.getText().toString());
                getApplicationContext().startService(intent);
            } else if (view.getId() == R.id.compute_button) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_2SecondaryActivity.class);
                intent.putExtra(Constants.ALL_TERMS, edit_text_all_terms.getText().toString());
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

            }
        }
    }
}
