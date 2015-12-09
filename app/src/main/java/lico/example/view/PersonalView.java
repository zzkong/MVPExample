package lico.example.view;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.Bind;
import lico.example.R;

/**
 * Created by zzk on 15/11/28.
 */
public class PersonalView extends ViewImpl {
    @Bind(R.id.backgroud)
    ImageView backgroud;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        collapsingToolbar.setTitle("Lico");
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);

    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void destroy() {

    }
}
