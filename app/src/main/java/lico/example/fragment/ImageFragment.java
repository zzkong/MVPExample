package lico.example.fragment;

import android.content.Context;

import lico.example.R;
import lico.example.presenter.FragmentPresenter;
import lico.example.view.ImagesView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zzk on 15/11/28.
 */
public class ImageFragment extends FragmentPresenter<ImagesView>{


    @Override
    protected void initData() {   //不是懒加载
        super.initData();
        getTitles(getActivity());
    }

    private void getTitles(Context context){

        Observable.just(R.array.images_category_list)
                .map(new Func1<Integer, String[]>() {

                    @Override
                    public String[] call(Integer integer) {
                        return context.getResources().getStringArray(R.array.images_category_list);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        mView.initViewPager(strings, getFragmentManager());
                    }
                });
    }
}
