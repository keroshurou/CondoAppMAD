package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditParcelDetails extends AppCompatActivity {

    ImageButton btnBack;
    EditText edtUpManagementID, edtUpCollectorName, edtUpUnitNumber, edtUpTrackingNumber, edtUpExpressBrand,
            edtUpDeliveredDate, edtUpCollectedStatus, edtUpCollectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parcel_details);

        btnBack = findViewById(R.id.btnBack);
        edtUpManagementID = findViewById(R.id.edtUpManagementID);
        edtUpCollectorName = findViewById(R.id.edtUpCollectorName);
        edtUpUnitNumber = findViewById(R.id.edtUpUnitNumber);
        edtUpTrackingNumber = findViewById(R.id.edtUpTrackingNumber);
        edtUpExpressBrand = findViewById(R.id.edtUpExpressBrand);
        edtUpDeliveredDate = findViewById(R.id.edtUpDeliveredDate);
        edtUpCollectedStatus = findViewById(R.id.edtUpCollectedStatus);
        edtUpCollectedDate = findViewById(R.id.edtUpCollectedDate);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(EditParcelDetails.this, ParcelListView.class);
                startActivity(intentBack);
            }
        });
    }
}