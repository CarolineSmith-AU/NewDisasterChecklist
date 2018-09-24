package cls0097.auburn.edu.newdisasterchecklist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
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
    Switch lightDarkThemeSwitch;

    public SharedPreferences preferences;

    //Name of file preferences will be saved in
    public static final String PREFS_NAME = "prefs";

    public static final String PREF_DARK_THEME = "dark_theme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(PREFS_NAME, 0);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            setTheme(R.style.AppTheme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightDarkThemeSwitch = findViewById(R.id.lightDarkThemeSwitch);
        lightDarkThemeSwitch.setChecked(useDarkTheme);
        lightDarkThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleAppTheme(isChecked);
            }
        });

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

    //Don't know how to apply this to other activities using SharedPreferences
    public void toggleAppTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        //editor.apply();
        editor.commit();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public static String getToggleTheme(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
