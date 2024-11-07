package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Colocviu1_2SecondaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.ALL_TERMS)) {
            String allTerms = intent.getStringExtra(Constants.ALL_TERMS);
            String[] termsArray = allTerms.split("\\+");

            int sum = 0;
            for (String term : termsArray) {
                sum += Integer.parseInt(term);
            }

            Toast.makeText(this, "Suma calculată este: " + sum, Toast.LENGTH_LONG).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("SUM_RESULT", sum);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}