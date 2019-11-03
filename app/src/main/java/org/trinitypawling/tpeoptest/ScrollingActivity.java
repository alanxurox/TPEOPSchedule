package org.trinitypawling.tpeoptest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private class MyView extends View {
        public MyView(ScrollingActivity scrollingActivity) {
            super(scrollingActivity);
        }

        public void onClick(View view) {

        }


        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            SettingsActivity settingsActivity = new SettingsActivity();
            int x = getWidth();
            int y = getHeight();
            int radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            Period.drawAllPeriods(canvas);

        }
    }
}
