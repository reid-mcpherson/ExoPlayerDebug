package com.google.android.exoplayer.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer.demo.debug.util.UiExecutor;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Module(injects = {PlayerActivity.class})
public class DemoModule {
    private final Context applicationContext;

    public DemoModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    Handler providesMainLooperHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    UiExecutor providesForegroundExecutor(Handler mainLooperHandler) {
        return new UiExecutor(mainLooperHandler);
    }

    @Provides
    @Singleton
    Executor providesBackgroundExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
