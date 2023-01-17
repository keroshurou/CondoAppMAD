package hajarshaufi.parcel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import hajarshaufi.parcel.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private ActivityOnBoardingBinding binding;
    boolean DoublePressToExit = false;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this,StaffLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (DoublePressToExit) {
            finishAffinity();
            toast.cancel();
        }else {
            DoublePressToExit = true;
            toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DoublePressToExit = false;
                }
            },1500);
        }
    }
}