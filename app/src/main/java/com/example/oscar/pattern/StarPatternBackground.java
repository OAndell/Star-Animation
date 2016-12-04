package com.example.oscar.pattern;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Oscar on 2016-12-04.
 */

public class StarPatternBackground extends View {

    private Paint paint = new Paint();
    private ArrayList<Star> stars = new ArrayList<>();

    private int numberOfStars = 50;
    private int width;
    private int height;
    private float speed = 1.05f;

    public StarPatternBackground(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        this.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        createStars();
        start(this);
    }

    private void createStars(){
        for (int i = 0; i < numberOfStars; i++) {
            stars.add(new Star(this));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, width, height, paint); //Paints whole view black
        for (int i = 0; i < numberOfStars; i++) {
            stars.get(i).draw(canvas);
        }
    }

    public int getCanvasWidth(){
        return width;
    }

    public int getCanvasHeight(){
        return height;
    }

    public float getSpeed(){
       return speed;
    }

    public void setSpeed(float speed){
        this.speed = speed;
        if(this.speed < 1){
            this.speed = 1;
        }
    }

    private void start(final StarPatternBackground bc){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bc.invalidate();
                handler.postDelayed(this, 20);
            }
        }, 20);
    }

}
