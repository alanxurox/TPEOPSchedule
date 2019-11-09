package org.trinitypawling.tpeoptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SWITCH = "switch";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COURSES = "courses";
    final List<Course> COURSES_List = new ArrayList<>();
    Intent settings;
    Intent week;
    Intent day;
    SharedPreferences sharedPreferences;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference courseRef = rootRef.child("Course");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        /*
        Attempted to get the SharedPreferences reference, tried both two, didn't work.
         */
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Log.i("info", "" + sharedPreferences);
        Log.i("info", "" + sharedPreferences.getAll());

        Period.periods = new ArrayList<>();
        //Attempted to read and load from SharedPreference
        loadData();
        updateViews();

        //Instantiate Drawable canvas to draw
        MyDrawable myDrawable = new MyDrawable();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(myDrawable);
        imageView.setContentDescription("a");


    }

    /**
     * Method to check the boolean and load periods accordingly
     */
    public void updateViews() {
        if (Period.AWeek())
            Period.loadPeriodsA();
        else
            Period.loadPeriodsB();
    }

    /**
     * SharedPreference method to load the data stored in settings activity
     */
    public void loadData() {

        Period.isAWeek = sharedPreferences.getBoolean(SWITCH, false);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(COURSES, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        SettingsActivity.schedule = gson.fromJson(json, type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStart() {
        super.onStart();
        MyDrawable myDrawable = new MyDrawable();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(myDrawable);
        imageView.setContentDescription("a");

        //Initialize the intent to go through the activities
        settings = new Intent(MainActivity.this, SettingsActivity.class);
        week = new Intent(MainActivity.this, ScrollingActivity.class);
        day = new Intent(MainActivity.this, DayActivity.class);

        /**
         * ValueEventListener that adds a course to the Course list arraylist, read from
         * the Firebase Database DataSnapshot whenever data changes.
         * To avoid duplicates, the size is set to 22, the amount of courses in the databse.
         */
        if (COURSES_List.size() < 22) {
            courseRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    //Log.i("info", "onDataChange is running" + dataSnapshot.getValue().toString());
                    //Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    Log.i("info", "onDataChange is running");
                    for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {

                        //Retrieve data and add to course
                        COURSES_List.add(new Course(

                                Integer.parseInt(courseSnapshot.child("Course").getValue(String.class)),

                                courseSnapshot.child("Teacher").getValue(String.class),

                                Integer.parseInt(courseSnapshot.child("Period").getValue(String.class)),

                                courseSnapshot.child("Classroom").getValue(String.class),

                                Integer.parseInt(courseSnapshot.child("Section").getValue(String.class)),

                                courseSnapshot.child("COURSE::name").getValue(String.class))
                        );
                    }
                    Course.setCourses(COURSES_List);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


    /**
     * Start settings activity intent
     *
     * @param view
     */
    public void onClickSettings(View view) {
        startActivity(settings);
    }

    /**
     * Start Scrolling activity (week) intent
     *
     * @param view
     */
    public void onClickWeek(View view) {
        startActivity(week);
    }

    /**
     * Start day activity intent
     * @param view
     */
    public void onClickDay(View view) {
        startActivity(day);
    }

}
