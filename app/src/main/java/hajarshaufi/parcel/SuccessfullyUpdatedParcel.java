package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SuccessfullyUpdatedParcel extends AppCompatActivity {

    ImageButton btnHome;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_updated_parcel);

        btnHome = findViewById(R.id.btnHome);
        btnView = findViewById(R.id.btnViewParcel);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(SuccessfullyUpdatedParcel.this, StaffHomeScreenActivity.class);
                startActivity(intentHome);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentView = new Intent(SuccessfullyUpdatedParcel.this, ParcelListView.class);
                startActivity(intentView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentView = new Intent(SuccessfullyUpdatedParcel.this, ParcelListView.class);
        startActivity(intentView);
    }
}