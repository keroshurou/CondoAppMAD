package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.parcel.databinding.ActivityMainBinding;

/***
 * Written by Israt
 * Login, Register, Forgot Password, Module
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });

        /**
         * binding.displayCard.setOnClickListener(new View.OnClickListener() {
         *             @Override
         *             public void onClick(View view) {
         *                 Intent i = new Intent(MainActivity.this,NotificationActivity.class);
         *                 startActivity(i);
         *            }
         *        });
         */

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        binding.parcelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentParcel = new Intent(MainActivity.this, ResidentParcelListView.class);
                startActivity(intentParcel);
            }
        });
    }
}