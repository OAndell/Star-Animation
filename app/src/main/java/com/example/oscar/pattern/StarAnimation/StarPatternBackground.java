package com.example.oscar.pattern.StarAnimation;

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

    private int numberOfStars = 200;
    private int width;
    private int height;
    private float speed = 1f;
    private float origin[] = new float[2];
    private boolean stop = false;
    private int updateSpeed = 2;

    public StarPatternBackground(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        origin[0] = width/2;
        origin[1] = height/2;
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

    public float[] getOrigin(){
        return origin;
    }

    public void setSpeed(float speed){
        this.speed = speed;
        if(this.speed < 1){
            this.speed = 1;
            stop = true;
        }
        else {
            stop = false;
        }
    }

    public void HandleRotation(float angleX, float angleY){
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).rotateX(angleX);
            stars.get(i).rotateY(angleY);
        }
    }

    protected ArrayList<Star> getStars(){
        return stars;
    }

    protected boolean isStopped(){
        return stop;
    }

    /**
     * Starts Animation
     */
    private void start(final StarPatternBackground bc){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bc.invalidate();
                handler.postDelayed(this, updateSpeed);
            }
        }, updateSpeed);
    }


}
