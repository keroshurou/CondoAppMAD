package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ParcelMainNavigation extends AppCompatActivity {

    VPAdapter vpAdapter;
    private TabLayout tabLayout;
    ViewPager2 viewPager2;
    private String[] titles = new String[] {"VIEW PARCEL", "ADD PARCEL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_main_navigation);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager);

        vpAdapter = new VPAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(titles[position]))).attach();

    }
}