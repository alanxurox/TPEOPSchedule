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
    /**
     * This activity is the week activity that shows all the classes
     * with a inner class that draws the periods
     * Alan
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * An inner class MyView that calls the onDraw method to draw on Canvas
     * Alan Xu
     */

    private class MyView extends View {
        public MyView(ScrollingActivity scrollingActivity) {
            super(scrollingActivity);
        }

        public void onClick(View view) {

        }

        /**
         * On instantiating the object, on draw is called
         *
         * @param canvas
         */

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

            ////Week's periods are drawn

            Period.drawAllPeriods(canvas);

        }
    }
}
