package com.naivor.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.naivor.app.extras.utils.LogUtil;
import com.naivor.app.presentation.di.component.ApplicationComponent;
import com.naivor.app.presentation.di.component.DaggerApplicationComponent;
import com.naivor.app.presentation.di.module.ApplicationModule;
import com.naivor.app.presentation.di.module.NetworkModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;


/**
 * AppApplication 程序的Application类，统领全局
 * <p/>
 * Created by tianlai on 16-3-3.
 */
public class AppApplication extends Application {

    private ApplicationComponent mAppComponent;

    @Inject
    CrashHandler crashHandler;

    @Inject
    OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        initInjector();

        injectApplication();

        ButterKnife.setDebug(BuildConfig.DEBUG);

        LogUtil.setDebugMode(BuildConfig.DEBUG);

        Fresco.initialize(this);

        crashHandler.init();

    }

    /**
     * 进行注入
     */
    private void injectApplication() {
        mAppComponent.inject(this);
    }

    /**
     * 初始化注入器
     */
    private void initInjector() {
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    /**
     * mAppComponent 的getter方法
     *
     * @return
     */
    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }


}
