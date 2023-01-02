package hajarshaufi.parcel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {

    private String[] titles = new String[] {"VIEW PARCEL", "ADD PARCEL"};

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new ParcelViewFragment();
            case 1:
                return new ParcelAddFragment();
        }
        return new ParcelViewFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
