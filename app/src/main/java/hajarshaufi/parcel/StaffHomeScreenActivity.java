package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.parcel.databinding.ActivityStaffHomeScreenBinding;

public class StaffHomeScreenActivity extends AppCompatActivity {

    ActivityStaffHomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_screen);

        binding = ActivityStaffHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
    }
}