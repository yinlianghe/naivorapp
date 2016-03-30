package com.naivor.app.presentation.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.naivor.app.ActivityManager;
import com.naivor.app.AppApplication;
import com.naivor.app.R;
import com.naivor.app.presentation.di.component.ActivityComponent;
import com.naivor.app.presentation.di.component.ApplicationComponent;
import com.naivor.app.presentation.di.component.DaggerActivityComponent;
import com.naivor.app.presentation.di.module.ActivityModule;
import com.naivor.app.presentation.presenter.BasePresenter;
import com.naivor.app.presentation.view.BaseUiView;
import com.naivor.widget.requestdialog.LoadingDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * BaseActivity 是所有activity的基类，把一些公共的方法放到里面
 * <p>
 * Created by tianlai on 16-3-3.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseUiView{

    private ActivityComponent activityComponent;

    @Inject
    protected LayoutInflater inflater;

    @Inject
    protected ActivityManager activityManager;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.rootView)
    protected LinearLayout rootView;

    protected BasePresenter presenter;

    protected LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        // 使用依赖注入
        ButterKnife.bind(this);
        initInjector();

        injectActivity(activityComponent);

        // 将当前activity加入ActivityManager中
        activityManager.addActivity(this);

        // 设置ActionBar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //初始化加载数据对话框
        loadingDialog=initLoadingDialog();

        if (loadingDialog!=null) {
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(true);

            loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface loadingDialog) {
                    presenter.cancleLoading();
                }
            });
        }

        //初始化Presenter
        presenter = getPresenter();
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为 Null");
        } else {
            presenter.oncreate(savedInstanceState);
        }


    }

    /**
     * 获取内容部分布局,并且加入根布局
     *
     * @return
     */
    protected void setContentToRoot(View view) {
        // 加载ContentView的内容
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.MATCH_PARENT);
        rootView.addView(view,params);
    }

    /**
     * 获取内容部分布局的layoutId,并且加入根布局
     *
     * @return
     */
    protected void setContentToRoot(int layoutId) {
        setContentToRoot(inflateView(layoutId));
    }

    /**
     * 获取展示加载状态的对话框
     *
     * @return
     */
    protected abstract LoadingDialog initLoadingDialog();

    /**
     * 进行注入
     *
     * @param activityComponent
     */
    protected abstract void injectActivity(ActivityComponent activityComponent);

    /**
     * 获取 ActivityModule
     *
     * @return
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 获取注入Activity的Presenter对象
     *
     * @return
     */
    protected abstract BasePresenter getPresenter();


    /**
     * 隐藏Toolbar
     */
    protected void hideToolbar() {

        toolbar.setVisibility(View.GONE);
    }

    /**
     * 把布局变成View
     *
     * @param layoutId
     * @return
     */
    protected View inflateView(int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    /**
     * 设置注入器
     *
     */
    private void initInjector(){
        activityComponent= DaggerActivityComponent.builder().applicationComponent(getAppComponent()).activityModule(new
                ActivityModule(this)).build();
    };


    /**
     * 获取 ApplicationComponent
     *
     * @return
     */
    private ApplicationComponent getAppComponent() {
        return ((AppApplication) getApplication()).getAppComponent();
    }

    /**
     *activityComponent的getter方法
     *
     * @return
     */
    public ActivityComponent getActivityComponent(){

        return activityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSave(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        activityManager.removeActivity(this);

        presenter.onDestroy();

    }
}