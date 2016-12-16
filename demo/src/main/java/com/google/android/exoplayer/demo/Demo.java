package com.google.android.exoplayer.demo;

import android.app.Application;
import com.google.android.exoplayer.demo.debug.util.DaggerObjectGraphProvider;
import dagger.ObjectGraph;

public class Demo extends Application implements DaggerObjectGraphProvider {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new DemoModule(this));
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
