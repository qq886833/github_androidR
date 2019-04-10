package com.bsoft.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsoft.baselib.widget.loadingstatusview.Gloading;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected Gloading.Holder mHolder;

    public View mainView;
  //  protected RxPermissions rxPermissions;
    public Context baseContext;
    public BaseActivity baseActivity;


    public boolean isCreated = false;//界面是否加载完成
    public boolean isLoaded = false;//数据是否加载过了
    public boolean isReceiver = false;//是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isReceiver = isVisibleToUser;
        if (!isCreated) {
            return;
        }
        if (isVisibleToUser) {
            startHint();
        } else {
            endHint();
        }
    }

    public abstract void startHint();

    public abstract void endHint();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseContext = getActivity();
        baseActivity = (BaseActivity) getActivity();
      //  rxPermissions = new RxPermissions(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mainView == null) {
            mainView = inflater.inflate(getLayoutRes(), container, false);
        }

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCreated = true;
        initView(mainView);

    }






























    /**
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     * @see
     */
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(getActivity()).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }
















    /**
     * 返回布局 resId
     *
     * @return layoutId
     */
    protected abstract int getLayoutRes();
    /**
     * 初始化view
     *
     * @param mainView
     */
    protected abstract void initView(View mainView);



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }









}
