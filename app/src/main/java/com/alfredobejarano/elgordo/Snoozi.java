package com.alfredobejarano.elgordo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class Snoozi extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
