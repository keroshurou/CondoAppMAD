package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class mainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void fnVisitorRegisteration(View view) {
        Intent intent = new Intent(getApplicationContext(), AddVisitor.class);
        startActivity(intent);

    }

    public void fnEditView(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewVisitor.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(mainPage.this, MainActivity.class);
        startActivity(intent);
    }
}