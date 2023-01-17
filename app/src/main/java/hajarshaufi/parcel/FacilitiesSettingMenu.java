package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FacilitiesSettingMenu extends AppCompatActivity {

    Button btnAddFacility, btnMaintenance, btnFacilitiesReport, btnEditFacility;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_setting_menu);

        //Get all Id's
        btnAddFacility = (Button) findViewById(R.id.btnFacility);
        btnMaintenance = (Button) findViewById(R.id.btnMaintenance);
        btnFacilitiesReport = (Button) findViewById(R.id.btnFacilitiesReport);
        btnEditFacility = (Button) findViewById(R.id.btnEditFacility);
        btnBack = findViewById(R.id.btnBack);

        //Intent to Add New Facility
        btnAddFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(FacilitiesSettingMenu.this,
                        AddNewFacility.class);
                startActivity(intentAdd);
            }
        });

        //Intent to Edit Facility
        btnEditFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(FacilitiesSettingMenu.this,
                        FacilitiesList.class);
                startActivity(intentAdd);
            }
        });


        //Intent to Facilities Maintenance
        btnMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(FacilitiesSettingMenu.this,
                        AddNewMaintenance.class);
                startActivity(intentAdd);
            }
        });

        //Intent to Facilities Booking Report
        btnFacilitiesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(FacilitiesSettingMenu.this,
                        Facilities_Report.class);
                startActivity(intentAdd);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FacilitiesSettingMenu.this, StaffHomeScreenActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FacilitiesSettingMenu.this, StaffHomeScreenActivity.class);
        startActivity(intent);
    }
}