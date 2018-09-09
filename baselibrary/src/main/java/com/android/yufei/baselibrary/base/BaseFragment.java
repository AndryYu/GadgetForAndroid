package com.android.yufei.baselibrary.base;

import com.android.yufei.baselibrary.mvp.IBasePresenter;
import com.android.yufei.baselibrary.mvp.IBaseView;
import com.trello.rxlifecycle2.components.support.RxFragment;

public abstract class BaseFragment<T extends IBasePresenter>
        extends RxFragment
        implements IBaseView<T> {


}
