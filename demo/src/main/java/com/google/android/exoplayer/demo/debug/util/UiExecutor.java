package com.google.android.exoplayer.demo.debug.util;

import android.os.Handler;

import java.util.concurrent.Executor;

public class UiExecutor implements Executor {

    private final Handler mainLooperHandler;

    public UiExecutor(Handler mainLooperHandler) {
        this.mainLooperHandler = mainLooperHandler;
    }

    @Override
    public void execute(Runnable command) {
        mainLooperHandler.post(command);
    }
}
