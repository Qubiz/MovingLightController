package qubiz.movinglightcontroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[] {"TAB 1", "TAB 2", "TAB 3"};

    public TabsFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
