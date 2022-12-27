package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ParcelAddSuccess extends AppCompatActivity {

    Button btnAddAnotherParcel, btnView;
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_add_success);

        btnAddAnotherParcel = findViewById(R.id.btnAddAnotherParcel);
        btnView = findViewById(R.id.btnView);
        btnHome = findViewById(R.id.btnHome);

        btnAddAnotherParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAnother = new Intent(ParcelAddSuccess.this, ParcelAdd.class);
                startActivity(intentAnother);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAnother = new Intent(ParcelAddSuccess.this, ParcelListView.class);
                startActivity(intentAnother);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAnother = new Intent(ParcelAddSuccess.this, ParcelManagementMain.class);
                startActivity(intentAnother);
            }
        });

    }
}