package lico.example.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import lico.example.presenter.IPresenter;

/**
 * Created by zzk on 15/11/26.
 */
public abstract class ViewImpl implements IView {
    protected View mRootView;
    protected IPresenter mPresenter;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView.getRootView());
    }

    @Override
    public View getRootView() { return mRootView; }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public Toolbar getToolbar() { return null; }

    @Override
    public void bindPresenter(IPresenter presenter) { mPresenter = presenter; }

    @Override
    public void bindEvent() {}

    public abstract int getLayoutId();

}
