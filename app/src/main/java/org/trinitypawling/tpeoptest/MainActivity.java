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

    final List<Course> COURSES = new ArrayList<>();
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String WEEK = "week";
    public static final String COURSE = "courses";
    Intent settings;
    Intent week;
    Intent day;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference courseRef = rootRef.child("Course");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        MyDrawable myDrawable = new MyDrawable();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(myDrawable);
        imageView.setContentDescription("a");

        this.loadData();
        this.updateViews();

    }

    public void updateViews() {
        if (Period.isAWeek)
            Period.loadPeriodsA();
        else
            Period.loadPeriodsB();
    }

    //TODO read saved schedule from settingsActivity when program starts.
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        Period.isAWeek = sharedPreferences.getBoolean(WEEK, false);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(COURSE, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        SettingsActivity.schedule = gson.fromJson(json, type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(WEEK, Period.AWeek());
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyDrawable myDrawable = new MyDrawable();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(myDrawable);
        imageView.setContentDescription("a");

        settings = new Intent(MainActivity.this, SettingsActivity.class);
        week = new Intent(MainActivity.this, ScrollingActivity.class);
        day = new Intent(MainActivity.this, DayActivity.class);

        final Integer courseNo;
        String teacher;
        Integer period;
        String classRoom;
        Character term;
        Integer section;
        String name;

        courseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                //Log.i("info", "onDataChange is running" + dataSnapshot.getValue().toString());
                //Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Log.i("info", "onDataChange is running");
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    COURSES.add(new Course(
                            Integer.parseInt(courseSnapshot.child("Course").getValue(String.class)),
                            courseSnapshot.child("Teacher").getValue(String.class),
                            Integer.parseInt(courseSnapshot.child("Period").getValue(String.class)),
                            courseSnapshot.child("Classroom").getValue(String.class),
                            Integer.parseInt(courseSnapshot.child("Section").getValue(String.class)),
                            courseSnapshot.child("COURSE::name").getValue(String.class))
                    );
                }
                Course.setCourses(COURSES);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //todo SWITCH DATA!!!
    public void onClickSettings(View view) {
        startActivity(settings);
    }

    public void onClickWeek(View view) {
        startActivity(week);
    }


    public void onClickDay(View view) {
        startActivity(day);
    }

}
