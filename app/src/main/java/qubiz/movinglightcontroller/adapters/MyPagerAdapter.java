package qubiz.movinglightcontroller.adapters;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import qubiz.movinglightcontroller.R;
import qubiz.movinglightcontroller.fragments.ColorPickerFragment;

public class MyPagerAdapter extends FragmentPagerAdapter{

    final String[] tabNames = new String[] {
            "TAB 1",
            "TAB 2",
            "Tab 3"
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
        ColorPickerFragment colorPickerFragment = ColorPickerFragment.newInstance(position);
        return colorPickerFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
