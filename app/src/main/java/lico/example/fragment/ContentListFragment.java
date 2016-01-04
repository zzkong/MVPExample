package lico.example.fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import lico.example.bean.JcodeInfo;
import lico.example.presenter.FragmentPresenter;
import lico.example.utils.DataUtil;
import lico.example.view.ContentListView;
import lico.example.views.PullLoadMoreRecyclerView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentListFragment extends FragmentPresenter<ContentListView> implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    String tid;
    boolean isRefresh = true;
    int page = 1;

    public static ContentListFragment newInstance(String id) {
        ContentListFragment contentListFragment = new ContentListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tid", id);
        contentListFragment.setArguments(bundle);
        return contentListFragment;
    }

    @Override
    protected void lazyData() {
        super.lazyData();

        mView.initViews(getActivity(), this);
        getData();
    }

    private void getData() {
        tid = getArguments().getString("tid");
        Observable.just("")
                .map(new Func1<String, List<JcodeInfo>>() {
                    @Override
                    public List<JcodeInfo> call(String s) {
                        return DataUtil.getJcodeData(tid, page);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<List<JcodeInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<JcodeInfo> jcodeInfos) {
                        Log.e("x", "xx :  " + jcodeInfos.size());
                        if (isRefresh) mView.refreshListData(jcodeInfos);
                        else mView.addListData(jcodeInfos);
                    }
                });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        page++;
        getData();
    }
}
