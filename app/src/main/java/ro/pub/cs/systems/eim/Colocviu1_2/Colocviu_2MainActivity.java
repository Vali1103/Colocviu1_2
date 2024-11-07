package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu_2MainActivity extends AppCompatActivity {
    EditText edit_text_next_term;
    EditText edit_text_all_terms;
    Button add_button, compute_button;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        edit_text_next_term = findViewById(R.id.Next_term);
        edit_text_all_terms = findViewById(R.id.all_terms);
        add_button = findViewById(R.id.add_button);
        compute_button = findViewById(R.id.compute_button);
    }

    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.add_button:
                    String all_terms = edit_text_all_terms.getText().toString();
                    String next_term = edit_text_next_term.getText().toString();
                    if (all_terms.isEmpty()) {
                        all_terms = next_term;
                    } else {
                        all_terms += "+" + next_term;
                    }
                    edit_text_all_terms.setText(all_terms);
                    break;
                case R.id.compute_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu_2Service.class);
                    intent.putExtra(Constants.ALL_TERMS, edit_text_all_terms.getText().toString());
                    getApplicationContext().startService(intent);
                    break;
            }
        }
    }
}
