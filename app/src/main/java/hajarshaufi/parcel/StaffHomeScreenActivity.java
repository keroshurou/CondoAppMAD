package hajarshaufi.parcel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
}