package cls0097.auburn.edu.newdisasterchecklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import AdapterRecyclerViewBasics.BasicsRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    TextView welcomeTextView;
    TextView toYourTextView;
    TextView emergencyTextView;
    TextView disasterTextView;
    TextView checklistTextView;
    TextView letsStartWithTheTextView;
    TextView basicsTextView;
    Button clickHereToBeginPreparingButton;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        toYourTextView = findViewById(R.id.toYourTextView);
        emergencyTextView = findViewById(R.id.emergencyTextView);
        disasterTextView = findViewById(R.id.disasterTextView);
        checklistTextView = findViewById(R.id.checklistTextView);
        letsStartWithTheTextView = findViewById(R.id.letsStartWithTheTextView);
        basicsTextView = findViewById(R.id.basicsTextView);
        startButton = findViewById(R.id.startButton);
        clickHereToBeginPreparingButton = findViewById(R.id.clickHereToBeginPreparing);

        clickHereToBeginPreparingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHereToBeginPreparingButton.setVisibility(View.GONE);
                fadeOutAnimation();
                fadeInAnimation();

                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toBasicsRecyclerView = new Intent(getApplicationContext(), BasicsRecyclerViewActivity.class);
                        startActivity(toBasicsRecyclerView);
                    }
                });
            }

            private void fadeOutAnimation() {
                Animation tvAnimation = new AlphaAnimation(1.0f, 0.0f);
                tvAnimation.setDuration(2000);
                tvAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        welcomeTextView.setVisibility(View.INVISIBLE);
                        toYourTextView.setVisibility(View.INVISIBLE);
                        emergencyTextView.setVisibility(View.INVISIBLE);
                        disasterTextView.setVisibility(View.INVISIBLE);
                        checklistTextView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                welcomeTextView.startAnimation(tvAnimation);
                toYourTextView.startAnimation(tvAnimation);
                emergencyTextView.startAnimation(tvAnimation);
                disasterTextView.startAnimation(tvAnimation);
                checklistTextView.startAnimation(tvAnimation);
            }

            private void fadeInAnimation() {
                Animation tvAnimation = new AlphaAnimation(0.0f, 1.0f);
                Animation btnAnimation = new AlphaAnimation(0.0f, 1.0f);
                tvAnimation.setDuration(2000);
                btnAnimation.setDuration(1000);
                tvAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        letsStartWithTheTextView.setVisibility(View.VISIBLE);
                        basicsTextView.setVisibility(View.VISIBLE);
                        startButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                letsStartWithTheTextView.startAnimation(tvAnimation);
                basicsTextView.startAnimation(tvAnimation);
                startButton.startAnimation(btnAnimation);
            }
        });
    }
}
