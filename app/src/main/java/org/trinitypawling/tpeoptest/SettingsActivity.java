package org.trinitypawling.tpeoptest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COURSES = "courses";
    public static final String SWITCH = "switch";
    ArrayList<Course> schedule = new ArrayList<>();
    Switch aSwitch;
    Set<String> courses;
    boolean bWeek;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, Course.getNameList(i));
            AutoCompleteTextView editText = findViewById(rID);
            editText.setAdapter(adapter);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
        final Switch aSwitch = findViewById(R.id.switchB);
        if (aSwitch.isChecked())
            Period.loadPeriodsB();
        else
            Period.loadPeriodsA();

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            AutoCompleteTextView editText = findViewById(rID);
            for (Course course : Course.getCourses()) {
                if (editText.getText().equals(course.getName()) && i == course.getPeriod())
                    schedule.add(course);
            }
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> courses = new HashSet<>();

        for (Course course : schedule) {
            courses.add(course.getName());
        }

        editor.putStringSet(COURSES, courses);
        editor.putBoolean(SWITCH, aSwitch.isChecked());
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        courses = sharedPreferences.getStringSet(COURSES, new HashSet<String>() {
        });
        bWeek = sharedPreferences.getBoolean(SWITCH, false);

    }

    public void updateViews() {
        //TODO load the text view from hash set/arraylist?
        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            AutoCompleteTextView editText = findViewById(rID);
            //editText.setText();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        aSwitch = findViewById(R.id.switchB);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    Period.loadPeriodsB();
                    bWeek = true;
                } else {
                    Period.loadPeriodsA();
                    bWeek = false;
                }
            }
        });
    }
}
