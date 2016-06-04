package com.alfredobejarano.elgordo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * This class handles all the default behaviors that needs to be changed from
 * the default Android's Application class.
 */
public class MyApplication extends Application {
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
