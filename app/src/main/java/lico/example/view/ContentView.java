package lico.example.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lico.example.R;
import lico.example.adapter.ContentPagerAdapter;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentView extends ViewImpl {
    @Bind(R.id.content_tab)
    TabLayout tabs;
    @Bind(R.id.content_viewpager)
    ViewPager viewpager;

    private ContentPagerAdapter mPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_content;
    }

    public void initViewPager(String[] titles, String[] ids, FragmentManager fragmentManager) {
        viewpager.setOffscreenPageLimit(titles.length + 1);
        List<String> tids = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (String titles1 : titles) {
            title.add(titles1);
        }
        for (String id : ids) {
            tids.add(id);
        }
        mPagerAdapter = new ContentPagerAdapter(fragmentManager, title, tids);
        viewpager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    public void destroy() {

    }
}
