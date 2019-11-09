package org.trinitypawling.tpeoptest;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This is a Drawable class that, when instantiated, draws the period schedule.
 */
public class MyDrawable extends Drawable {
    @Override
    public void draw(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        WTime now = new WTime();
        Period.drawMainPeriods(canvas, now);

    }


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
