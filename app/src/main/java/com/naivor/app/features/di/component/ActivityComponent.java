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

package com.naivor.app.features.di.component;

import com.naivor.app.common.base.BaseFragment;
import com.naivor.app.features.di.PerActivity;
import com.naivor.app.features.di.module.ActivityModule;
import com.naivor.app.modules.login.ui.LoginActivity;
import com.naivor.app.modules.login.ui.register.RegisterActivity;
import com.naivor.app.modules.login.ui.resetPsw.ResetPswActivity;
import com.naivor.app.modules.main.ui.MainActivity;
import com.naivor.app.modules.others.SplashActivity;

import dagger.Component;

/**
 * Created by tianlai on 16-3-9.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    ////要注入的类型
    void inject(BaseFragment baseFragment);

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(ResetPswActivity resetPswActivity);

    void inject(MainActivity mainActivity);

    //要暴露给其他依赖本组件的组件的依赖方法

    //子Component
}
