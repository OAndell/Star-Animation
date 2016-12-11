package com.example.oscar.pattern;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.oscar.pattern.StarAnimation.StarPatternBackground;
import com.example.oscar.pattern.StarAnimation.StarPatternBackgroundVR;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        final int screenHeight = size.y;


        LinearLayout layout = new LinearLayout(this);
        final StarPatternBackgroundVR backgroundCanvas = new StarPatternBackgroundVR(this, screenWidth, screenHeight, 200);
        backgroundCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getY() > screenHeight/2){
                    backgroundCanvas.setSpeed(backgroundCanvas.getSpeed() + 0.001f);
                }
                else{
                    backgroundCanvas.setSpeed(backgroundCanvas.getSpeed() - 0.001f);
                }
                return true;
            }
        });
        layout.addView(backgroundCanvas);
        setContentView(layout);
    }
}
