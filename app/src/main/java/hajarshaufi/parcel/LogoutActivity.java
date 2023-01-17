package hajarshaufi.parcel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class LogoutActivity extends AppCompatActivity {

    boolean DoublePressToExit = false;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
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

        /*Intent intent = new Intent(LogoutActivity.this, OnBoardingActivity.class);
        startActivity(intent);*/
    }
}