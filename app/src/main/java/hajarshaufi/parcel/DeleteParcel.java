package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeleteParcel extends AppCompatActivity {

    Button btnYes, btnNo;
    TextView txtView2, txtView;
    private int position;

    String url = "http://192.168.146.86/condoapp/deleteParcel.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_parcel);

        btnNo = findViewById(R.id.btnNo);
        btnYes = findViewById(R.id.btnYes);
        txtView2 = findViewById(R.id.txtView2);
        txtView = findViewById(R.id.txtView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        txtView2.setText(ParcelListView.parcelArrayList.get(position).getTrackingNumber());
        txtView.setText(ParcelListView.parcelArrayList.get(position).getCollectorName());

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(DeleteParcel.this, ParcelListView.class);
                startActivity(intentBack);
            }
        });
    }

    public void fnDelete (View view) {

        final String parcelID = ParcelListView.parcelArrayList.get(position).getParcelID();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(DeleteParcel.this, "Data Deleted Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DeleteParcel.this, "Data Not Deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DeleteParcel.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("parcelID", parcelID);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}