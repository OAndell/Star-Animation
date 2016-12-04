package com.example.oscar.pattern;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        final int screenHeight = size.y;

        LinearLayout layout = new LinearLayout(this);
        final StarPatternBackground backgroundCanvas = new StarPatternBackground(this, screenWidth, screenHeight);
        backgroundCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getY() > screenHeight/2){
                    backgroundCanvas.setSpeed(backgroundCanvas.getSpeed() + 0.01f);
                }
                else{
                    backgroundCanvas.setSpeed(backgroundCanvas.getSpeed() - 0.01f);
                }
                return true;
            }
        });
        layout.addView(backgroundCanvas);
        setContentView(layout);
    }
}
