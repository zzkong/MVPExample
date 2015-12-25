package lico.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lico.example.fragment.ContentListFragment;
import lico.example.fragment.ImageListFragment;

/**
 * Created by zwl on 2015/8/31.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mFragmentTitles = new ArrayList<>();
    private int mType;

    public TabFragmentPagerAdapter(FragmentManager fm, List<String> strings, int type) {
        super(fm);
        this.mFragmentTitles = strings;
        mType = type;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("x", "----------------------type: " + mType);
        if (mType == 1){
            Log.e("x", "xxxxxxxp2222222222222apggg");
            return ImageListFragment.newInstance(mFragmentTitles.get(position));
        } else {
            Log.e("x", "xxxxxxxp1111111111111111apggg");
            return ContentListFragment.newInstance(mFragmentTitles.get(position));
        }

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
