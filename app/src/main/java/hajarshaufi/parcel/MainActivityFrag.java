package hajarshaufi.parcel;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hajarshaufi.parcel.ui.main.SectionsPagerAdapter;
import hajarshaufi.parcel.databinding.ActivityMainFragBinding;

public class MainActivityFrag extends AppCompatActivity {

    private ActivityMainFragBinding binding;
    boolean DoublePressToExit = false;
    Toast toast;


    TabLayout tabs;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFragBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//Set and define the tabs
        tabs = binding.tabs;
        tabs.addTab(tabs.newTab().setText("Resident"));
        tabs.addTab(tabs.newTab().setText("Staff"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager =binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected (TabLayout.Tab tab){


                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected (TabLayout.Tab tab){
            }
            @Override
            public void onTabReselected (TabLayout.Tab tab){
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (DoublePressToExit) {
            finishAffinity();
            toast.cancel();
        }else {
            DoublePressToExit = true;
            toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DoublePressToExit = false;
                }
            },1500);
        }
    }
}