package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditParcelDetails extends AppCompatActivity {

    ImageButton btnBack;
    EditText edtUpCollectorName, edtUpUnitNumber, edtUpTrackingNumber, edtUpExpressBrand,
            edtUpDeliveredDate, edtUpCollectedStatus, edtUpCollectedDate;
    TextView edtParcelID;

    private int position;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parcel_details);

        btnBack = findViewById(R.id.btnBack);

        edtParcelID = findViewById(R.id.edtUpParcelID);
        edtUpCollectorName = findViewById(R.id.edtUpCollectorName);
        edtUpUnitNumber = findViewById(R.id.edtUpUnitNumber);
        edtUpTrackingNumber = findViewById(R.id.edtUpTrackingNumber);
        edtUpExpressBrand = findViewById(R.id.edtUpExpressBrand);
        edtUpDeliveredDate = findViewById(R.id.edtUpDeliveredDate);
        edtUpCollectedStatus = findViewById(R.id.edtUpCollectedStatus);
        edtUpCollectedDate = findViewById(R.id.edtUpCollectedDate);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edtParcelID.setText(ParcelListView.parcelArrayList.get(position).getParcelID());
        edtUpCollectorName.setText(ParcelListView.parcelArrayList.get(position).getCollectorName());
        edtUpUnitNumber.setText(ParcelListView.parcelArrayList.get(position).getParcelUnit());
        edtUpTrackingNumber.setText(ParcelListView.parcelArrayList.get(position).getTrackingNumber());
        edtUpExpressBrand.setText(ParcelListView.parcelArrayList.get(position).getExpressBrand());
        edtUpDeliveredDate.setText(ParcelListView.parcelArrayList.get(position).getDeliveredDate());
        edtUpCollectedStatus.setText(ParcelListView.parcelArrayList.get(position).getCollectStatus());
        edtUpCollectedDate.setText(ParcelListView.parcelArrayList.get(position).getCollectedDate());

        edtUpDeliveredDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //Initialize variable
                    final Calendar cldr = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = simpleDateFormat.format(cldr.getTime());

                    //Get day, month, year
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);

                    // date picker dialog
                    datePicker = new DatePickerDialog(EditParcelDetails.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    edtUpDeliveredDate.setText(year  + "-" + (monthOfYear + 1)
                                            + "-" + dayOfMonth);
                                }
                            }, year,month, day);
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePicker.show();
                }
                return false;
            }
        });

        edtUpCollectedDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //Initialize variable
                    final Calendar cldr = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = simpleDateFormat.format(cldr.getTime());

                    //Get day, month, year
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);

                    // date picker dialog
                    datePicker = new DatePickerDialog(EditParcelDetails.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    edtUpCollectedDate.setText(year  + "-" + (monthOfYear + 1)
                                            + "-" + dayOfMonth);
                                }
                            }, year,month, day);
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePicker.show();
                }
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(EditParcelDetails.this, ParcelListView.class);
                startActivity(intentBack);
            }
        });

    }

    public void fnUpdate(View view){

        final String parcelID = edtParcelID.getText().toString().trim();
        final String collectorName = edtUpCollectorName.getText().toString().trim();
        final String unitNumber = edtUpUnitNumber.getText().toString().trim();
        final String trackingNumber = edtUpTrackingNumber.getText().toString().trim();
        final String expressBrand = edtUpExpressBrand.getText().toString().trim();
        final String deliveredDate = edtUpDeliveredDate.getText().toString().trim();
        final String collectStatus = edtUpCollectedStatus.getText().toString().trim();
        final String collectedDate = edtUpCollectedDate.getText().toString().trim();

        // Give a warning to user when the field is empty
        if(TextUtils.isEmpty(collectorName)){
            edtUpCollectorName.setError("Please enter Collector Name");
        } else if(TextUtils.isEmpty(unitNumber)){
            edtUpUnitNumber.setError("Please enter House Unit Number");
        } else if(TextUtils.isEmpty(expressBrand)) {
            edtUpExpressBrand.setError("Please enter Express Brand (Courier Service)");
        } else if(TextUtils.isEmpty(trackingNumber)) {
            edtUpTrackingNumber.setError("Please enter Tracking Number");
        } else if(TextUtils.isEmpty(deliveredDate)) {
            edtUpDeliveredDate.setError("Please enter Delivered Date");
        } else if(TextUtils.isEmpty(collectStatus)) {
            edtUpCollectedStatus.setError("Please enter Collect Status");
         } else if(TextUtils.isEmpty(collectedDate)) {
            edtUpCollectedDate.setError("Please enter Collected Date");
         } else {
            Intent intentSuccess = new Intent(EditParcelDetails.this, SuccessfullyUpdatedParcel.class);
            startActivity(intentSuccess);
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.225/condoapp/updateParcel.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditParcelDetails.this, response,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ParcelListView.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditParcelDetails.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("parcelID", parcelID);
                params.put("collectorName", collectorName);
                params.put("parcelUnit", unitNumber);
                params.put("expressBrand", expressBrand);
                params.put("trackingNumber", trackingNumber);
                params.put("deliveredDate", deliveredDate);
                params.put("collectStatus", collectStatus);
                params.put("collectedDate", collectedDate);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditParcelDetails.this);
        requestQueue.add(request);
    }
}