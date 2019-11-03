package org.trinitypawling.tpeoptest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    boolean bWeek;
    //final String[] COURSES = Course.getNameList();
    ArrayList<Period> schedule = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        for (int j = 1; j <= 7; j++) {
            String viewID = "tv" + j;
            int resID = getResources().getIdentifier(viewID, "id", getPackageName());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, Course.getNameList(j));
            AutoCompleteTextView editText = findViewById(resID);
            editText.setAdapter(adapter);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        final Switch aSwitch = findViewById(R.id.switchB);
        if (aSwitch.isChecked())
            Period.loadPeriodsB();
        else
            Period.loadPeriodsA();


    }

    @Override
    protected void onStart() {
        super.onStart();
        final Switch aSwitch = findViewById(R.id.switchB);

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
