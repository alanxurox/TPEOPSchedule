package org.trinitypawling.tpeoptest;

import android.content.Intent;
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

/**
 * Settings Activity for user to set their own courses and switch between a and b week
 *
 * Alan Xu
 */

public class SettingsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COURSES = "courses";
    public static final String SWITCH = "switch";
    static ArrayList<String> schedule;
    static SharedPreferences sharedPreferences;
    Switch aSwitch;
    boolean bWeek;

    /**
     * Method called as the activity is created, used for initializing objects
     * and setting up for the autocompletetextview to grab data from the Firebase database
     *
     * @param savedInstanceState
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        schedule = new ArrayList<>();
        setContentView(R.layout.activity_settings);
        aSwitch = findViewById(R.id.switchB);

        //load and update data stored in shared preferences

        loadData();
        updateViews();

        //Loops through the auto complete text views, and set the according subjects to the correct periods

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());

            //Get the arraylist from the Course class

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, Course.getNameList(i));

            AutoCompleteTextView editText = findViewById(rID);
            editText.setAdapter(adapter);

        }


    }

    public void onCommit(View view) {
        schedule.clear();

        //Iterates through the autocompletetextviews with string tv + i, which is the id of the text views

        for (int i = 1; i <= 7; i++) {
            String viewID = "tv" + i;
            int rID = getResources().getIdentifier(viewID, "id", getPackageName());
            AutoCompleteTextView editText = findViewById(rID);

            //add to the schedule array list

            schedule.add(editText.getText().toString());
        }

        Period.isAWeek = aSwitch.isChecked();


        saveData();
    }


    /**
     * On exit, this method stores the data, loads the periods into the graphics
     */

    protected void onDestroy() {
        super.onDestroy();

        if (Period.isAWeek)
            Period.loadPeriodsA();
        else
            Period.loadPeriodsB();

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        //startActivity(intent);


    }

    /**
     * Method store data using Google GSON and sharedPreferences
     */

    public void saveData() {
//        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Utilize a google library that converts array list into json format for storage

        Gson gson = new Gson();
        String json = gson.toJson(schedule);

        //put in the converted json arraylist and bool into the stored preference

        editor.putString(COURSES, json);
        editor.putBoolean(SWITCH, aSwitch.isChecked());
        editor.commit();
        editor.apply();
    }

    /**
     * Method load data on start
     */

    public void loadData() {
//        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(COURSES, null);

        //To load the arraylist, a type token that specifies the array list is needed

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        schedule = gson.fromJson(json, type);

        //Avoid null references
        if (schedule == null)
            schedule = new ArrayList<>();
        bWeek = sharedPreferences.getBoolean(SWITCH, false);

    }

    /**
     * Method set the correct week boolean and the auto complete text view
     */

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


    /**
     * Method called on the startup of the activity,
     * set a listener for whenever the switch is clicked
     */

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
