package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailParcel extends AppCompatActivity {

    TextView txtID, txtName, txtParcelUnit, txtExpressBrand,
            txtTrackingNumber, txtDeliveredDate, txtCollectStatus, txtCollectedDate;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parcel);

        //Initializing Views
        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtParcelUnit = findViewById(R.id.txtParcelUnit);
        txtExpressBrand = findViewById(R.id.txtExpressBrand);
        txtTrackingNumber = findViewById(R.id.txtTrackingNumber);
        txtDeliveredDate = findViewById(R.id.txtDeliveredDate);
        txtCollectStatus = findViewById(R.id.txtCollectStatus);
        txtCollectedDate = findViewById(R.id.txtCollectedDate);

        btnBack = findViewById(R.id.btnBack);

        //Intent to Parcel List
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(DetailParcel.this,
                        ParcelListView.class);
                startActivity(intentBack);
            }
        });

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");

        txtID.setText("ID: "+ParcelListView.parcelArrayList.get(position).getParcelID());
        txtName.setText("Collector Name: "+ParcelListView.parcelArrayList.get(position).getCollectorName());
        txtParcelUnit.setText("Parcel Unit: "+ParcelListView.parcelArrayList.get(position).getParcelUnit());
        txtExpressBrand.setText("Express Brand: "+ParcelListView.parcelArrayList.get(position).getExpressBrand());
        txtTrackingNumber.setText("Tracking Number: "+ParcelListView.parcelArrayList.get(position).getTrackingNumber());
        txtDeliveredDate.setText("Delivered Date: "+ParcelListView.parcelArrayList.get(position).getDeliveredDate());
        txtCollectStatus.setText("Collect Status: "+ParcelListView.parcelArrayList.get(position).getCollectStatus());
        txtCollectedDate.setText("Collected Date: "+ParcelListView.parcelArrayList.get(position).getCollectedDate());

    }
}