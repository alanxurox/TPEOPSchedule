package org.trinitypawling.tpeoptest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    private class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            Period.drawTodayPeriods(canvas);

        }
    }

}
