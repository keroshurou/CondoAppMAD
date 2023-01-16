package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResidentDetailParcel extends AppCompatActivity {

    TextView txtID, txtName, txtParcelUnit, txtExpressBrand,
            txtTrackingNumber, txtDeliveredDate, txtCollectStatus, txtCollectedDate;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_detail_parcel);

        //Initializing Views
        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtParcelUnit = findViewById(R.id.txtParcelUnit);
        txtExpressBrand = findViewById(R.id.txtExpressBrand);
        txtTrackingNumber = findViewById(R.id.txtTrackingNumber);
        txtDeliveredDate = findViewById(R.id.txtDeliveredDate);
        txtCollectStatus = findViewById(R.id.txtCollectStatus);
        txtCollectedDate = findViewById(R.id.txtCollectedDate);

        //Intent to Parcel List
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(ResidentDetailParcel.this,
                        ResidentParcelListView.class);
                startActivity(intentBack);
            }
        });

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");

        txtID.setText("ID: "+ResidentParcelListView.residentParcelArrayList.get(position).getParcelID());
        txtName.setText("Collector Name: "+ResidentParcelListView.residentParcelArrayList.get(position).getCollectorName());
        txtParcelUnit.setText("Parcel Unit: "+ResidentParcelListView.residentParcelArrayList.get(position).getParcelUnit());
        txtExpressBrand.setText("Express Brand: "+ResidentParcelListView.residentParcelArrayList.get(position).getExpressBrand());
        txtTrackingNumber.setText("Tracking Number: "+ResidentParcelListView.residentParcelArrayList.get(position).getTrackingNumber());
        txtDeliveredDate.setText("Delivered Date: "+ResidentParcelListView.residentParcelArrayList.get(position).getDeliveredDate());
        txtCollectStatus.setText("Collect Status: "+ResidentParcelListView.residentParcelArrayList.get(position).getCollectStatus());
        txtCollectedDate.setText("Collected Date: "+ResidentParcelListView.residentParcelArrayList.get(position).getCollectedDate());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(ResidentDetailParcel.this,
                ResidentParcelListView.class);
        startActivity(intentBack);
    }
}