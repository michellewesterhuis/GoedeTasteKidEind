package nl.tschout.tastekid.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by thierry on 10/23/2015.
 */
public class tastekidAdapter extends FragmentPagerAdapter {
    final Fragment[] pages = new Fragment[] {
            new tastekidFragment(),
            new tastekidFragment()
    };
    final String[] pageTitles = new String[] {
            "Recent",
            "Library"
    };

    public tastekidAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }
}
