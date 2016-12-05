package com.example.oscar.pattern.StarAnimation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * Created by Oscar on 2016-12-05.
 */

public class StarPatternBackgroundVR extends StarPatternBackground {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    public StarPatternBackgroundVR(Context context, int width, int height) {
        super(context, width, height);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                HandleRotation(sensorEvent.values[1], sensorEvent.values[0]); // Get the rotation speed from Gyroscope
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {}
        }, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_GAME );
    }



}
