package lico.example.fragment;

import android.content.Context;

import lico.example.R;
import lico.example.presenter.FragmentPresenter;
import lico.example.view.ContentView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentFragment extends FragmentPresenter<ContentView>{

    @Override
    protected void initData() {
        super.initData();
        getTitle(getActivity());
    }
    
    private void getTitle(Context context){
        Observable.just(R.array.images_category_list)
                .map(new Func1<Integer, String[]>() {

                    @Override
                    public String[] call(Integer integer) {
                        return context.getResources().getStringArray(integer);
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
