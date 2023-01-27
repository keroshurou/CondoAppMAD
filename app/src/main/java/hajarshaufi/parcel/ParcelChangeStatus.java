package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

public class ParcelChangeStatus extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvParcelID, tvCollectorName, tvUnitNumber, tvTrackingNumber, tvExpressBrand, tvDeliveredDate, textViewStatus;
    ImageButton btnBack;
    EditText edtUpCollectedDate;

    private int position;
    private DatePickerDialog datePicker;
    private Spinner statusSpinner;
    String url = "http://192.168.59.86/condoapp/updateParcel.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_change_status);

        btnBack = findViewById(R.id.btnBack);

        tvParcelID = findViewById(R.id.tvParcelID);
        tvCollectorName = findViewById(R.id.tvCollectorName);
        tvUnitNumber = findViewById(R.id.tvUnitNumber);
        tvTrackingNumber = findViewById(R.id.tvTrackingNumber);
        tvExpressBrand = findViewById(R.id.tvExpressBrand);
        tvDeliveredDate = findViewById(R.id.tvDeliveredDate);
        textViewStatus = findViewById(R.id.txtViewEmpty);
        edtUpCollectedDate = findViewById(R.id.edtUpCollectedDate);

        statusSpinner = findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(ParcelChangeStatus.this,
                R.array.collectStatus, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvParcelID.setText(ParcelListView.parcelArrayList.get(position).getParcelID());
        tvCollectorName.setText(ParcelListView.parcelArrayList.get(position).getCollectorName());
        tvUnitNumber.setText(ParcelListView.parcelArrayList.get(position).getParcelUnit());
        tvTrackingNumber.setText(ParcelListView.parcelArrayList.get(position).getTrackingNumber());
        tvExpressBrand.setText(ParcelListView.parcelArrayList.get(position).getExpressBrand());
        tvDeliveredDate.setText(ParcelListView.parcelArrayList.get(position).getDeliveredDate());
        textViewStatus.setText(ParcelListView.parcelArrayList.get(position).getCollectStatus());
        edtUpCollectedDate.setText(ParcelListView.parcelArrayList.get(position).getCollectedDate());

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
                    datePicker = new DatePickerDialog(ParcelChangeStatus.this,
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
                Intent intentBack = new Intent(ParcelChangeStatus.this, ParcelListView.class);
                startActivity(intentBack);
            }
        });

    }

    public void fnUpdate(View view){

        final String parcelID = tvParcelID.getText().toString().trim();
        final String collectorName = tvCollectorName.getText().toString().trim();
        final String unitNumber = tvUnitNumber.getText().toString().trim();
        final String trackingNumber = tvTrackingNumber.getText().toString().trim();
        final String expressBrand = tvExpressBrand.getText().toString().trim();
        final String deliveredDate = tvDeliveredDate.getText().toString().trim();
        final String collectStatus = statusSpinner.getSelectedItem().toString().trim();
        final String collectedDate = edtUpCollectedDate.getText().toString().trim();

        if(TextUtils.isEmpty(collectedDate)) {
            edtUpCollectedDate.setError("Please enter Collected Date");
        } else {
            Intent intentSuccess = new Intent(ParcelChangeStatus.this, SuccessfullyUpdatedParcel.class);
            startActivity(intentSuccess);
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParcelChangeStatus.this, response,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ParcelListView.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ParcelChangeStatus.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(ParcelChangeStatus.this);
        requestQueue.add(request);

        Intent intent = new Intent(ParcelChangeStatus.this, SuccessfullyUpdatedParcel.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}