package AdapterRecyclerViewAdditionals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cls0097.auburn.edu.newdisasterchecklist.MainActivity;
import cls0097.auburn.edu.newdisasterchecklist.R;

public class AdditionalsActivity extends AppCompatActivity {

    TextView nowTextView;
    TextView letsMoveOnToTextView;
    TextView additionalTextView;
    TextView suppliesTextView;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionals);

        MainActivity.getToggleTheme(MainActivity.PREFS_NAME, getApplicationContext());
        nowTextView = findViewById(R.id.nowTextView);
        letsMoveOnToTextView = findViewById(R.id.letsMoveOnToTextview);
        additionalTextView = findViewById(R.id.additionalTextView);
        suppliesTextView = findViewById(R.id.suppliesTextView);
        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdditionalsRecyclerViewActivity = new Intent(AdditionalsActivity.this, AdditionalsRecyclerViewActivity.class);
                startActivity(toAdditionalsRecyclerViewActivity);
            }
        });
    }
}
