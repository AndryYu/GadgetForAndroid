package com.android.yufei.baselibrary.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IBaseView<T> {

    /**
     * <p>bindToLife</p>
     * @Description 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
