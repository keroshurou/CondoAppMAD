package hajarshaufi.parcel;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddVisitor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] vehicleType = {"NONE", "MotorCycle", "Car"};
    String managementApproval="Pending";
    String parkingBooking="None";
    String url = "http://192.168.26.86/condoapp/addVisitor.php";

    Spinner edtVehicleType;
    DatePickerDialog.OnDateSetListener setListener;

    // creating variables for our edit text
    private EditText edtVisitorName, edtParkingNumber, edtPhoneNumber, edtCheckInDate, edtCheckOutDate, edtParty, edtPlateNumber, edtLicenceNumber;

    // creating variable for button
    private Button btnDone;

    // creating a strings for storing our values from edittext fields.
    private String VisitorName, CheckInDate, CheckOutDate, VehicleType, PlateNumber, Licence_Number, ParkingNumber,ApproveParking;

    private int PhoneNumber, No_of_Visitors;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor);

        // initializing our edittext and buttons
        edtVisitorName = findViewById(R.id.edtVisitorName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtCheckInDate = findViewById(R.id.edtCheckInDate);
        edtCheckOutDate = findViewById(R.id.edtCheckOutDate);
        edtParty = findViewById(R.id.edtParty);
        edtPlateNumber = findViewById(R.id.edtPlateNumber);
        edtLicenceNumber = findViewById(R.id.edtLicenceNumber);
        edtVehicleType = findViewById(R.id.edtVehicleType);
        btnDone = findViewById(R.id.btnEdit);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edtCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddVisitor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        edtCheckInDate.setText(date);
                    }
                }, year, month, day);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


        edtCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddVisitor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        edtCheckOutDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetVisitor();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicletype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtVehicleType.setAdapter(adapter);
        edtVehicleType.setOnItemSelectedListener(this);

    }

    public void GetVisitor() {
        // getting data from edittext fields.
        VisitorName = edtVisitorName.getText().toString();
        CheckInDate = edtCheckInDate.getText().toString();
        CheckOutDate = edtCheckOutDate.getText().toString();
        PlateNumber = edtPlateNumber.getText().toString();
        Licence_Number = edtLicenceNumber.getText().toString();
        ParkingNumber = parkingBooking;
        ApproveParking = managementApproval;
        try {
            PhoneNumber = Integer.parseInt(edtPhoneNumber.getText().toString());
            No_of_Visitors = Integer.parseInt(edtParty.getText().toString());
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }



        // validating the text fields if empty or not.
        if (TextUtils.isEmpty(VisitorName)) {
            edtVisitorName.setError("Please enter visitor Name");
        } else if (TextUtils.isEmpty(String.valueOf(PhoneNumber))) {
            edtPhoneNumber.setError("Please enter Date");
        } else if (TextUtils.isEmpty(CheckInDate)) {
            edtCheckInDate.setError("Please enter Date");
        } else if (TextUtils.isEmpty(CheckOutDate)) {
            edtCheckOutDate.setError("Please enter Date");
        }  else if (TextUtils.isEmpty(String.valueOf(No_of_Visitors))) {
            edtParty.setError("Please enter Date");
        }  else if (TextUtils.isEmpty(PlateNumber)) {
            edtPlateNumber.setError("Please enter plate number");
        } else if (TextUtils.isEmpty(Licence_Number)) {
            edtLicenceNumber.setError("Please enter licence number");
        } else {
            // calling method to add data to Firebase Firestore.
            addDataToDatabase(VisitorName, Integer.parseInt(String.valueOf(PhoneNumber)), CheckInDate, CheckOutDate, Integer.parseInt(String.valueOf(No_of_Visitors)), VehicleType, PlateNumber, Licence_Number, ParkingNumber, ApproveParking);
            //Intent to Successful Added  Screen
            Intent intentSuccess = new Intent(AddVisitor.this, SuccessfullyAdded.class);
            startActivity(intentSuccess);
        }
    }

    private void addDataToDatabase(String VisitorName, int PhoneNumber, String CheckInDate, String CheckOutDate, int No_of_Visitors, String VehicleType, String PlateNumber, String Licence_Number, String ParkingNumber,String ApproveParking) {

        // url to post our data

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(AddVisitor.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(AddVisitor.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                edtVisitorName.setText("");
                edtPhoneNumber.setText("");
                edtCheckInDate.setText("");
                edtCheckOutDate.setText("");
                edtParty.setText("");
                edtPlateNumber.setText("");
                edtLicenceNumber.setText("");

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddVisitor.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("visitorName", VisitorName);
                params.put("phoneNumber", String.valueOf(PhoneNumber));
                params.put("checkInDate", CheckInDate);
                params.put("checkOutDate", CheckOutDate);
                params.put("noOfVisitors", String.valueOf(No_of_Visitors));
                params.put("vehicleType", VehicleType);
                params.put("plateNumber", PlateNumber);
                params.put("licenseNumber", Licence_Number);
                params.put("parkingNumber", ParkingNumber);
                params.put("approveParking",ApproveParking);

                // at last we are returning our params.
                return params;
            }

        };

        // below line is to make
        // a json object request.
        queue.add(request);
    }


    public void fnBack(View view) {

        Intent intent = new Intent(getApplicationContext(), mainPage.class);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        VehicleType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AddVisitor.this, mainPage.class);
        startActivity(intent);
    }
}





