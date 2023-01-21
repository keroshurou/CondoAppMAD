package hajarshaufi.parcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import hajarshaufi.parcel.databinding.ActivityStaffHomeScreenBinding;

public class StaffHomeScreenActivity extends AppCompatActivity {

    ActivityStaffHomeScreenBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_screen);

        binding = ActivityStaffHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StaffHomeScreenActivity.this);
                alertDialog.setTitle("Exit App");
                alertDialog.setMessage("Do you want to exit");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(StaffHomeScreenActivity.this, LogoutActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        });*/

        binding.staffParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentParcel = new Intent(StaffHomeScreenActivity.this, ParcelManagementMain.class);
                startActivity(intentParcel);
            }
        });

        binding.staffFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentParcel = new Intent(StaffHomeScreenActivity.this, FacilitiesSettingMenu.class);
                startActivity(intentParcel);
            }
        });

        binding.staffVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentParcel = new Intent(StaffHomeScreenActivity.this, ViewVisitorManagement.class);
                startActivity(intentParcel);
            }
        });
        //binding.btnNotification.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {
        // Intent i = new Intent(StaffHomeScreenActivity.this,StaffNotificationActivity.class);
        //startActivity(i);
        //}
        // });

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open,
                R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    /*case R.id.nav_notification:
                        intent = new Intent(getApplicationContext(), NotificationSlider.class);
                        startActivity(intent);
                        return true;*/

                    case R.id.nav_logout:

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StaffHomeScreenActivity.this);
                        alertDialog.setTitle("Exit App");
                        alertDialog.setMessage("Do you want to exit");
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(StaffHomeScreenActivity.this, LogoutActivity.class);
                                startActivity(intent);
                            }
                        });
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();

                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StaffHomeScreenActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(StaffHomeScreenActivity.this, LogoutActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}