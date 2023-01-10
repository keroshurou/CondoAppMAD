package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessfullyDeleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_deleted_visitor);
    }

    public void fnDoneDelete(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewVisitor.class);
        startActivity(intent);
    }
}