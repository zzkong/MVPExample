package lico.example.fragment;

import android.os.Bundle;

import lico.example.bean.ResponseImagesListEntity;
import lico.example.listener.HttpApi;
import lico.example.presenter.FragmentPresenter;
import lico.example.view.ContentListView;
import lico.example.views.PullLoadMoreRecyclerView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentListFragment extends FragmentPresenter<ContentListView> implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    String keyword;
    boolean isRefresh = true;
    int page = 0;

    public static ContentListFragment newInstance(String title){
        ContentListFragment contentListFragment = new ContentListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", title);
        contentListFragment.setArguments(bundle);
        return contentListFragment;
    }

    @Override
    protected void lazyData() {
        super.lazyData();
        mView.initViews(getActivity(), this);
        getData();
    }

    private void getData(){
        keyword = getArguments().getString("keyword");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://image.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApi api = retrofit.create(HttpApi.class);
        api.getImagess(keyword, "全部", page*10, 10, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ResponseImagesListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseImagesListEntity entity) {
                        if(isRefresh) mView.refreshListData(entity.imgs);
                        else mView.addListData(entity.imgs);
                    }
                });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 0;
        getData();
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        page++;
        getData();
    }
}
