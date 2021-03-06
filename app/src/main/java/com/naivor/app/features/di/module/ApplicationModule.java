/*
 * Copyright (c) 2016. Naivor.All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.naivor.app.features.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import com.naivor.app.NaivorApp;
import com.naivor.loadmore.LoadMoreHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationModule  Activity的模块，为Activity里面需要自动实例化的类提供依赖
 * <p>
 * Created by tianlai on 16-3-3.
 */

@Module
public class ApplicationModule {
    private NaivorApp mApplication;

    public ApplicationModule(NaivorApp application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mApplication;
    }


    @Singleton
    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mApplication);
    }

    @Provides
    LoadMoreHelper provideLoadMoreHelper() {
        return new LoadMoreHelper(mApplication);
    }


}
