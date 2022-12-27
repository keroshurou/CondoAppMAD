package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditParcelDetails extends AppCompatActivity {

    ImageButton btnBack;
    EditText editManagementID, editCollectorName, editParcelUnit, editExpressBrand, editTrackingNumber, editDeliveredDate,
    editCollectStatus, editCollectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parcel_details);
    }
}