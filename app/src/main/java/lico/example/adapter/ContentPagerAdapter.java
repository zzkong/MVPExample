package lico.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import lico.example.fragment.ContentListFragment;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mFragmentTitles = new ArrayList<>();
    private List<String> tids = new ArrayList<>();

    public ContentPagerAdapter(FragmentManager fm, List<String> strings, List<String> ids) {
        super(fm);
        this.mFragmentTitles = strings;
        this.tids = ids;
    }

    @Override
    public Fragment getItem(int position) {
        return ContentListFragment.newInstance(tids.get(position));
    }

    @Override
    public int getCount() {
        return mFragmentTitles == null ? 0 : mFragmentTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles == null ? null : mFragmentTitles.get(position);
    }
}
