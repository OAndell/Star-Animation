package com.example.oscar.pattern.StarAnimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Oscar on 2016-12-04.
 * @// TODO: 2016-12-05 remove magic numbers
 */

public class Star {
    private float[] positionVector = new float[3]; //3d vector
    private float[] previousPosVector = new float[2];

    private Random rnd = new Random();
    private Paint paint = new Paint();
    private StarPatternBackground background;
    private int maxStartRadius = 10;

    public Star(StarPatternBackground background){
        this.background = background;
        positionVector[0] = background.getOrigin()[0] - rnd.nextInt(background.getCanvasWidth());
        positionVector[1] = background.getOrigin()[1] - rnd.nextInt(background.getCanvasHeight());
        positionVector[2] = rnd.nextInt(maxStartRadius);
        previousPosVector[0] = positionVector[0];
        previousPosVector[1] = positionVector[1];
        paint.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(translateX(positionVector[0]),translateY(positionVector[1]), positionVector[2],paint);
        canvas.drawLine(translateX(previousPosVector[0]), translateY(previousPosVector[1]), translateX(positionVector[0]), translateY(positionVector[1]), paint);
        update();
    }

    /**
     * Translate the x-coordinate to the canvas coordinate system.
     */
    private float translateX(float x){
        return background.getOrigin()[0] + x;
    }

    /**
     * Translate the y-coordinate to the canvas coordinate system.
     */
    private float translateY(float y){
        return background.getOrigin()[1] + y;
    }

    public void rotateX(float x){
        float offset = x*(10+ positionVector[2]);
        positionVector[0] += offset;
        previousPosVector[0] += offset;
    }

    public void rotateY(float y){
        float offset = y*(10+ positionVector[2]);
        positionVector[1] += offset;
        previousPosVector[1] += offset;
    }


    private void update(){
         previousPosVector[0] = positionVector[0];
         previousPosVector[1] = positionVector[1];
         positionVector[0] *= background.getSpeed() + positionVector[2]*0.005; //Faster when closer;
         positionVector[1] *= background.getSpeed() + positionVector[2]*0.005;
         positionVector[2] *= background.getSpeed() + positionVector[2]*0.005;
         paint.setStrokeWidth(positionVector[2]);
        if(!isInsideCanvas()){
            reset();
        }

    }

    /**
     * Check if star is inside the screen
     */
    private boolean isInsideCanvas(){
        if(positionVector[0] > background.getCanvasWidth()){
            return false;
        }
        if(positionVector[0] < -background.getCanvasWidth()){
            return false;
        }
        if(positionVector[1] > background.getCanvasHeight()){
            return false;
        }
        if(positionVector[1] < -background.getCanvasHeight()){
            return false;
        }
        else{
            return true;
        }
    }

    private void reset(){
        positionVector[0] = background.getOrigin()[0] - rnd.nextInt(background.getCanvasWidth());
        positionVector[1] = background.getOrigin()[1] - rnd.nextInt(background.getCanvasHeight());
        positionVector[2] = 1;

        previousPosVector[0] = positionVector[0];
        previousPosVector[1] = positionVector[1];
    }
}
