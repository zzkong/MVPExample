package lico.example.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lico.example.fragment.LazyFragment;
import lico.example.helper.GenericHelper;
import lico.example.view.IView;

/**
 * Created by zzk on 15/11/26.
 */
public class FragmentPresenter<T extends IView> extends LazyFragment implements IPresenter<T>{

    protected T mView;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try{
            mView = getViewClass().newInstance();
            mView.create(inflater, container);
            mView.bindPresenter(this);
            view = mView.getRootView();
            initData();
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }



    @Override
    protected void initializeData() {//懒加载在这里,so在写个方法让子类去实现
        lazyData();
    }

    @Override
    public void onDestroyView() {
        onDestroyV();
        mView = null;
        super.onDestroyView();
    }

    protected void onDestroyV() {
    }

    protected void initData(){}

    protected void lazyData(){}

    @Override
    public Class<T> getViewClass() {
        return GenericHelper.getViewClass(getClass());
    }
}
