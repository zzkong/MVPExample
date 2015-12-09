package lico.example.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import lico.example.R;
import lico.example.presenter.ActivityPresenter;
import lico.example.utils.BitmapUtil;
import lico.example.view.MainView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends ActivityPresenter<MainView>
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void initData() {
        super.initData();
        getBitmap();
        mView.setNavigationItemSelected(getSupportFragmentManager(), R.id.nav_camera);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mView.setOptionsItemSelected(item.getItemId());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return mView.setNavigationItemSelected(getSupportFragmentManager(), item.getItemId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mView.setFabClick("我的天呐");
                break;
            case R.id.imageView:
                Intent intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mView.closeDrawer())
            return;
        super.onBackPressed();
    }

    private void getBitmap() {
        Observable.just(R.mipmap.avator)
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapUtil.matrixBitmap(MainActivity.this, integer);
                    }
                })
                .map(new Func1<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap call(Bitmap bitmap) {
                        return BitmapUtil.blurBitmap(MainActivity.this, bitmap, 15.5f);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mView.setAvator(bitmap);
                    }
                });
    }
}
