package org.trinitypawling.tpeoptest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    final String[] COURSES = Course.getNameList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout vl = findViewById(R.id.vl);
        final int childCount = vl.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (vl.getChildAt(i).getId() != R.id.space) {
                AutoCompleteTextView editText = (AutoCompleteTextView) vl.getChildAt(i);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, COURSES);
                editText.setAdapter(adapter);
            }

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
                if (aSwitch.isChecked())
                    Period.loadPeriodsB();
                else
                    Period.loadPeriodsA();
            }
        });
    }
}
