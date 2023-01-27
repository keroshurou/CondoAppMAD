package hajarshaufi.parcel.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import hajarshaufi.parcel.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @SuppressLint("SupportAnnotationUsage")
    @StringRes
//private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private int totalTabs;
    public SectionsPagerAdapter (Context context, FragmentManager fm, int totalTabs) {
        super (fm);
        mContext =context;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
// getItem is called to instantiate the fragment for the given page.
// Return a Placeholder Fragment (defined as a static inner class below).
// return Placeholder Fragment.newInstance(position + 1);
        switch (position) {
            case 0:
                Resident resident = new Resident();
                return resident;
            case 1:
                Staff staff = new Staff();
                return staff;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

// Show 2 total pages.
        return totalTabs;
    }
}