package lico.example.fragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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
public class ContentFragment extends FragmentPresenter<ContentView> {

    private String[] ids = new String[]{"6", "18", "5", "27", "14"};

    @Override
    protected void initData() {
        super.initData();
        getTitle(getActivity());
    }

    private void getTitle(Context context) {
        Observable.just(1)
                .map(new Func1<Integer, List<String[]>>() {

                    @Override
                    public List<String[]> call(Integer integer) {
                        return getList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String[]>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String[]> list) {
                        mView.initViewPager(list.get(0), list.get(1), getChildFragmentManager());
                    }
                });
    }

    private List<String[]> getList() {
        List<String[]> list = new ArrayList<>();
        list.add(getResources().getStringArray(R.array.archive_list));
        list.add(ids);

        return list;
    }
}
