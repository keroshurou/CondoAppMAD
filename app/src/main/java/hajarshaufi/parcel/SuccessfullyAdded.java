package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessfullyAdded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_added);
    }

    public void fnDone(View view) {

        Intent intent = new Intent(getApplicationContext(), mainPage.class);
        startActivity(intent);

    }
}