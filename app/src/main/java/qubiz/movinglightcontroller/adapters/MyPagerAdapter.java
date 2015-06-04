package qubiz.movinglightcontroller.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import qubiz.movinglightcontroller.R;
import qubiz.movinglightcontroller.fragments.ColorPickerFragment;

public class MyPagerAdapter extends FragmentPagerAdapter{

    private final int TAB_COUNT = 2;

    final String[] tabNames = new String[] {
            "COLOR PICKER",
            "PRESETS",
    };

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public Fragment getItem(int position) {
        return ColorPickerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
