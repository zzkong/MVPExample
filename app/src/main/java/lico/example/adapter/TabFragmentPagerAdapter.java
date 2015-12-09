package lico.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lico.example.fragment.ImageListFragment;

/**
 * Created by zwl on 2015/8/31.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mFragmentTitles = new ArrayList<>();

    public TabFragmentPagerAdapter(FragmentManager fm, List<String> strings) {
        super(fm);
        this.mFragmentTitles = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageListFragment.newInstance(mFragmentTitles.get(position));
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
