package hajarshaufi.parcel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rejected extends AppCompatActivity {

    private String VisitorName,ParkingNumber,ApproveParking;
    Visitor visitor;
    List<Visitor> visitorList;
    String url = "http://192.168.1.113/condoapp/Approve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejected);

        if (getIntent() != null) {
            visitor = (Visitor) getIntent().getSerializableExtra("visitor");

            VisitorName = visitor.getVisitorName();

        }


        String ParkingLot = "Rejected";
        String ApproveStatus = "Rejected";
        ApproveParking=ApproveStatus;
        ParkingNumber=ParkingLot;
        fnUpdate(VisitorName,ParkingNumber,ApproveParking);
    }

    public void fnUpdate( String VisitorName , String ParkingNumber, String ApproveParking) {

        final ProgressDialog progressDialog = new ProgressDialog(Rejected.this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Rejected.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Rejected.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("visitorName", VisitorName);
                params.put("parkingNumber", ParkingNumber);
                params.put("approveParking",ApproveParking);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Rejected.this);
        requestQueue.add(request);
    }

    public void fnDoneUpdate(View view) {
        Intent intentReject = new Intent(Rejected.this, ViewVisitorManagement.class);
        startActivity(intentReject);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intentBack = new Intent(Rejected.this,
                ViewVisitorManagement.class);
        startActivity(intentBack);
    }
}