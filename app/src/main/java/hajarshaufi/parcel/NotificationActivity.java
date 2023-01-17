package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.parcel.databinding.ActivityNotificationBinding;

/***
 * Written by Israt
 * Login, Register, Forgot Password, Module
 */

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;
    private String[] head = {"Water Disruption","Electric Disruption","Overdue Maintenance"};
    private String[] sub = {"Water Disruption On___","Electric Disruption On___",
            "Overdue Maintenance On___"};
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtHead.setText(head[counter]);
        binding.txtSub.setText(sub[counter]);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentBack = new Intent(NotificationActivity.this,
                        MainActivity.class);
                startActivity(intentBack);
            }
        });

        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                if (counter < 0) counter = head.length - 1;
                binding.txtHead.setText(head[counter]);
                binding.txtSub.setText(sub[counter]);
            }
        });

        binding.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                if (counter == head.length) counter = 0;
                binding.txtHead.setText(head[counter]);
                binding.txtSub.setText(sub[counter]);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(NotificationActivity.this,
                MainActivity.class);
        startActivity(intentBack);
    }
}