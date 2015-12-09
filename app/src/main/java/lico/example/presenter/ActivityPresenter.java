package lico.example.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import lico.example.helper.GenericHelper;
import lico.example.view.IView;

/**
 * Created by zzk on 15/11/26.
 */
public class ActivityPresenter<T extends IView> extends AppCompatActivity implements IPresenter<T> {

    protected T mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mView = getViewClass().newInstance();
            mView.create(getLayoutInflater(), null);
            mView.bindPresenter(this);
            setContentView(mView.getRootView());
            initToolbar();
            mView.bindEvent();
            initData();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void initToolbar() {
        Toolbar toolbar = mView.getToolbar();
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mView.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(mView.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        mView.destroy();
        super.onDestroy();
    }

    protected void initData(){}

    @Override
    public Class<T> getViewClass() {
        return GenericHelper.getViewClass(getClass());
    }

}
