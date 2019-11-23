package org.trinitypawling.tpeoptest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
        
            //Background image tp logo
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.logo_blugold);
            super.onDraw(canvas);
            int x = bitmap.getWidth();
            int y = bitmap.getHeight();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(2);

            //Find the dimensions of the background image and the screen size
            Rect source, dest;
            source = new Rect(0, 0, x, y);//size of bitmap
            dest = new Rect(0, 0, this.getWidth(), this.getHeight());//size of this view

            //Draw image and scale it to the size of the screen, dest
            canvas.drawBitmap(bitmap, source, dest, null);//scale bitmap from src to dst
            ////Week's periods are drawn

            Period.drawAllPeriods(canvas);

        }
    }
}
