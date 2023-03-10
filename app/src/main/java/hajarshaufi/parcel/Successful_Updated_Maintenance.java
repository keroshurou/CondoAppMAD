package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Successful_Updated_Maintenance extends AppCompatActivity {

    ImageButton btnBack, btnHome;
    Button btnViewMList, btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_updated_maintenance);

        //Get all Id's
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnViewMList = (Button) findViewById(R.id.btnViewMaintenanceList);
        btnDone = (Button) findViewById(R.id.btnDone);

        //Intent to Edit Maintenance Info
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(Successful_Updated_Maintenance.this,
                        Edit_maintenance_info.class);
                startActivity(intentBack);
            }
        });

        //Intent to Facility Menu
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(Successful_Updated_Maintenance.this,
                        FacilitiesSettingMenu.class);
                startActivity(intentBack);
            }
        });

        //Intent to Maintenance List
        btnViewMList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(Successful_Updated_Maintenance.this,
                        MaintenanceList.class);
                startActivity(intentBack);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(Successful_Updated_Maintenance.this,
                        StaffHomeScreenActivity.class);
                startActivity(intentBack);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(Successful_Updated_Maintenance.this,
                Edit_maintenance_info.class);
        startActivity(intentBack);
    }
}