package lico.example.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import lico.example.presenter.IPresenter;

/**
 * Created by zzk on 15/11/26.
 */
public interface IView {

    void create(LayoutInflater inflater, ViewGroup container);

    View getRootView();

    int getOptionsMenuId();

    Toolbar getToolbar();

    void bindPresenter(IPresenter presenter);

    void bindEvent();

    void destroy();
}
