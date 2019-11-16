package org.trinitypawling.tpeoptest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.logo_bg);
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(2);
            canvas.drawBitmap(bitmap.copy(Bitmap.Config.ARGB_8888, true), 0, 0, paint);

            ////Week's periods are drawn

            Period.drawAllPeriods(canvas);

        }
    }
}
