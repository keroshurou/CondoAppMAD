package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ParcelManagementMain extends AppCompatActivity {

    Button insertParcelBtn, parcelReportBtn;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_management_main);

        insertParcelBtn = findViewById(R.id.insertParcelBtn);
        parcelReportBtn = findViewById(R.id.parcelReportBtn);
        homeBtn = findViewById(R.id.homeBtn);

        //intent to insert parcel
        insertParcelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentParcel = new Intent(ParcelManagementMain.this, ParcelAdd.class);
                startActivity(intentParcel);
            }
        });

        //intent to parcel report
        parcelReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentParcelReport = new Intent(ParcelManagementMain.this, ParcelListView.class);
                startActivity(intentParcelReport);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParcelManagementMain.this, StaffHomeScreenActivity.class);
                startActivity(intent);
            }
        });

    }

}