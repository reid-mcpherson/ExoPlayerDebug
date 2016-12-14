package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.demo.debug.ManifestProvider;
import dagger.Module;
import dagger.Provides;

@Module(injects = {PlayerActivity.class})
public class DemoModule {

    @Provides
    public ManifestProvider providesManifestProvider() {
        return new ManifestProvider();
    }
}
