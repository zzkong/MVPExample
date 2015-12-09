package lico.example.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lico.example.R;
import lico.example.adapter.TabFragmentPagerAdapter;

/**
 * Created by zzk on 15/12/1.
 */
public class ImagesView extends ViewImpl {
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private TabFragmentPagerAdapter mPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_images;
    }

    public void initViewPager(String[] titles, FragmentManager manager){
        viewpager.setOffscreenPageLimit(titles.length + 1);
        List<String> title = new ArrayList<>();
        for (String titles1 : titles)
            title.add(titles1);
        mPagerAdapter = new TabFragmentPagerAdapter(manager, title);
        viewpager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    public void destroy() {

    }
}
