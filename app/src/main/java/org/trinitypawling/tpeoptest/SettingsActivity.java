package org.trinitypawling.tpeoptest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COURSES = "courses";
    public static final String SWITCH = "switch";
    static ArrayList<String> schedule = new ArrayList<>();
    ArrayList<Course> courseList = new ArrayList<>();
    Switch aSwitch;
    boolean bWeek;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aSwitch = findViewById(R.id.switchB);

        loadData();
        updateViews();

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
        schedule.clear();

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            AutoCompleteTextView editText = findViewById(rID);

            schedule.add(editText.getText().toString());
        }

        if (aSwitch.isChecked())
            Period.loadPeriodsB();
        else
            Period.loadPeriodsA();


        saveData();

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(schedule);

        editor.putString(COURSES, json);
        editor.putBoolean(SWITCH, aSwitch.isChecked());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(COURSES, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        schedule = gson.fromJson(json, type);

        bWeek = sharedPreferences.getBoolean(SWITCH, false);

    }

    public void updateViews() {

        aSwitch.setChecked(bWeek);

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            AutoCompleteTextView editText = findViewById(rID);

            try {
                editText.setText(schedule.get(i - 1));
            } catch (IndexOutOfBoundsException e) {
                Log.i("info", "schedule not filled yet");
            } catch (NullPointerException e) {
                Log.i("info", "schedule item at " + i + " is empty");
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        aSwitch = findViewById(R.id.switchB);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bWeek = aSwitch.isChecked();
            }
        });
        loadData();
    }
}
