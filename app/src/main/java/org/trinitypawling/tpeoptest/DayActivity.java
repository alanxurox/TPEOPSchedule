package org.trinitypawling.tpeoptest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity draws today's schedule only with a MyView class
 * Alan Xu
 */

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    /**
     * A inner MyView class that draws on Canvas
     * Alan Xu
     */

    private class MyView extends View {
        public MyView(Context context) {
            super(context);
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

            //Today's periods are drawn

            int today = new WTime().getDay();
            Period.drawTodayPeriods(canvas, today);

        }
    }

}
