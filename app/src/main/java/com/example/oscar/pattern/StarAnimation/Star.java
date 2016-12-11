package com.example.oscar.pattern.StarAnimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Oscar on 2016-12-04.
 */

public class Star {
    private float[] positionVector = new float[3]; //3d vector (x,y,z)
    private float[] previousPosVector = new float[2]; //2d vector (x,y)

    private Random rnd = new Random();
    private Paint paint = new Paint();
    private StarPatternBackground background;
    private int maxStartRadius = 10;
    private int deafultDistanceFromScreen = 10;
    private double accelerationConstant = 0.005; //Not really acceleration.

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
        canvas.drawCircle(translateX(positionVector[0]),translateY(positionVector[1]), positionVector[2],paint); //Draw star
        canvas.drawLine(translateX(previousPosVector[0]), translateY(previousPosVector[1]), translateX(positionVector[0]), translateY(positionVector[1]), paint); //Draw line to previus pos
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
        float offset = x*(deafultDistanceFromScreen + positionVector[2]);
        positionVector[0] += offset;
        previousPosVector[0] += offset;
    }

    public void rotateY(float y){
        float offset = y*(deafultDistanceFromScreen+ positionVector[2]);
        positionVector[1] += offset;
        previousPosVector[1] += offset;
    }


    /**
     * Update star to new pos. Increase speed. Reset if outside screen
     */
    private void update(){
         previousPosVector[0] = positionVector[0];
         previousPosVector[1] = positionVector[1];
         positionVector[0] *= background.getSpeed() + positionVector[2]*accelerationConstant; //Faster when closer;
         positionVector[1] *= background.getSpeed() + positionVector[2]*accelerationConstant;
         positionVector[2] *= background.getSpeed() + positionVector[2]*accelerationConstant;
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

    /**
     * Reset the positionvector by giving it a random new position.
     */
    private void reset(){
        positionVector[0] = background.getOrigin()[0] - rnd.nextInt(background.getCanvasWidth());
        positionVector[1] = background.getOrigin()[1] - rnd.nextInt(background.getCanvasHeight());
        positionVector[2] = 1;

        previousPosVector[0] = positionVector[0];
        previousPosVector[1] = positionVector[1];
    }
}
