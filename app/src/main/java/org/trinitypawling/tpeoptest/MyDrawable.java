package org.trinitypawling.tpeoptest;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This is a Drawable class that, when instantiated, draws the period schedule.
 * Alan Xu
 */

public class MyDrawable extends Drawable {
    @Override
    public void draw(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        WTime now = new WTime();

        //Draw the periods with the current canvas and time
        if (Period.periods.size() > 0)
            Period.drawMainPeriods(canvas, now);

    }



    /*
    All default methods that comes with the Drawable interface below
     */

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        int i = 0;
        return i;
    }
}
