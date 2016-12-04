package com.example.oscar.pattern;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Oscar on 2016-12-04.
 */

public class Star {

    private float[] origin = new float[2]; //zero vector
    private float[] positionVector = new float[3]; //3d vector

    private float[] previousPosVector = new float[2];

    private Random rnd = new Random();
    private Paint paint = new Paint();
    private StarPatternBackground background;

    public Star(StarPatternBackground background){
        origin[0] = background.getCanvasWidth()/2;
        origin[1] = background.getCanvasHeight()/2;
        this.background = background;
        positionVector[0] = origin[0] - rnd.nextInt(background.getCanvasWidth());
        positionVector[1] = origin[1] - rnd.nextInt(background.getCanvasHeight());
        positionVector[2] = rnd.nextInt(5);

        previousPosVector[0] = positionVector[0];
        previousPosVector[1] = positionVector[1];

        paint.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas){

        canvas.drawCircle(origin[0] + positionVector[0],origin[1] + positionVector[1], positionVector[2],paint);
        canvas.drawLine(origin[0] + previousPosVector[0], origin[1] + previousPosVector[1], origin[0] +positionVector[0], origin[1] +positionVector[1], paint);
        update();
    }


    private void update(){
       previousPosVector[0] = positionVector[0];
       previousPosVector[1] = positionVector[1];
       positionVector[0] *= background.getSpeed();
       positionVector[1] *= background.getSpeed();
       positionVector[2] *= background.getSpeed();
       paint.setStrokeWidth(positionVector[2]);
       if(!isInsideCanvas()){
           reset();
       }
    }

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
        positionVector[0] = origin[0] - rnd.nextInt(background.getCanvasWidth());
        positionVector[1] = origin[1] - rnd.nextInt(background.getCanvasHeight());
        positionVector[2] = 1;

        previousPosVector[0] = positionVector[0];
        previousPosVector[1] = positionVector[1];

    }


}
