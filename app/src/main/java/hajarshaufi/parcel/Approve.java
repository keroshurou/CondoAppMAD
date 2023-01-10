package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Approve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve);
    }

    public void fnDoneApproved(View view) {
        Intent intentReject = new Intent(Approve.this, ViewVisitorManagement.class);
        startActivity(intentReject);
    }
}