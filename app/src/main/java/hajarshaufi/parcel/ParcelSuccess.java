package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParcelSuccess extends AppCompatActivity {

    Button addAnotherParcel, btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_success);

        addAnotherParcel = findViewById(R.id.btnAddAnotherParcel);
        btnDone = findViewById(R.id.btnDone);

        addAnotherParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentAddAnother = new Intent(ParcelSuccess.this, ParcelDetails.class);
                startActivity(intentAddAnother);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDone = new Intent(ParcelSuccess.this, MainActivity.class);
                startActivity(intentDone);
            }
        });
    }
}