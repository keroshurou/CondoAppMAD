package hajarshaufi.parcel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditVisitor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] vehicleType = {"NONE", "MOTORCYCLE", "CAR"};
    Spinner vehicleTypeSpin;
    Button btnUpdate;
    Visitor visitor;
    private String VisitorName, CheckInDate, CheckOutDate, VehicleType, PlateNumber, Licence_Number, ParkingNumber,ApproveParking;

    private int VisitorID,PhoneNumber, No_of_Visitors;

    EditText etVisitorName, etPhoneNumber, etCheckInDate, etCheckOutDate, etNoOfVisitors, etPlateNumber, etLicenceNumber, etParkingNumber;

    String managementApproval = "Pending";
    String parkingLot = "None";
    String url = "http://10.131.77.18/condoapp/viewVisitor.php";
    String url1 = "http://10.131.77.18/condoapp/updateVisitor.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_visitor);

        etVisitorName = findViewById(R.id.etVisitorName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etNoOfVisitors = findViewById(R.id.etNoOfVisitors);
        etPlateNumber = findViewById(R.id.etPlateNumber);
        etLicenceNumber = findViewById(R.id.etLicenceNumber);
        etCheckInDate = findViewById(R.id.etCheckInDate);
        etCheckOutDate = findViewById(R.id.etCheckOutDate);
        vehicleTypeSpin = findViewById(R.id.etVehicleType);
        btnUpdate = findViewById(R.id.btnEdit);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting data from edittext fields.
                VisitorName = etVisitorName.getText().toString();
                CheckInDate = etCheckInDate.getText().toString();
                CheckOutDate = etCheckOutDate.getText().toString();
                PlateNumber = etPlateNumber.getText().toString();
                VehicleType = vehicleTypeSpin.getSelectedItem().toString();
                Licence_Number = etLicenceNumber.getText().toString();
                try {
                    PhoneNumber = Integer.parseInt(etPhoneNumber.getText().toString());
                    No_of_Visitors = Integer.parseInt(etNoOfVisitors.getText().toString());
                }catch (NumberFormatException nfe){
                    nfe.printStackTrace();
                }
                ApproveParking=managementApproval;
                ParkingNumber=parkingLot;




                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(VisitorName)) {
                    etVisitorName.setError("Please enter visitor Name");
                } else if (TextUtils.isEmpty(String.valueOf(PhoneNumber))) {
                    etPhoneNumber.setError("Please enter Date");
                } else if (TextUtils.isEmpty(CheckInDate)) {
                    etCheckInDate.setError("Please enter Date");
                } else if (TextUtils.isEmpty(CheckOutDate)) {
                    etCheckOutDate.setError("Please enter Date");
                }  else if (TextUtils.isEmpty(String.valueOf(No_of_Visitors))) {
                    etNoOfVisitors.setError("Please enter Date");
                } else if (TextUtils.isEmpty(PlateNumber)) {
                    etPlateNumber.setError("Please enter plate number");
                } else if (TextUtils.isEmpty(Licence_Number)) {
                    etLicenceNumber.setError("Please enter licence number");
                }  else {
                    // calling method to add data to Firebase Firestore.
                    fnUpdate(Integer.parseInt(String.valueOf(VisitorID)),VisitorName, Integer.parseInt(String.valueOf(PhoneNumber)), CheckInDate, CheckOutDate, Integer.parseInt(String.valueOf(No_of_Visitors)), String.valueOf(VehicleType), PlateNumber, Licence_Number, ParkingNumber,ApproveParking);
                    //Intent to Successful Added  Screen
                    Intent intentSuccess = new Intent(EditVisitor.this, SuccessFullyUpdate.class);
                    startActivity(intentSuccess);
                }


            }
        });

        if (getIntent() != null) {
            visitor = (Visitor) getIntent().getSerializableExtra("visitor");

            VisitorName=visitor.getVisitorName();

        }

        getVisitorInformation(VisitorName);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditVisitor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        etCheckInDate.setText(date);
                    }
                }, year, month, day);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


        etCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditVisitor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        etCheckOutDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicletype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpin.setAdapter(adapter);
        vehicleTypeSpin.setOnItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intentBack = new Intent(EditVisitor.this, ViewVisitor.class);
        startActivity(intentBack);
    }

    public void getVisitorInformation(String visitorName){


        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(EditVisitor.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are checking if the response is null or not.
                    if (jsonObject.getString("visitorName") == null) {
                        // displaying a toast message if we get error
                        Toast.makeText(EditVisitor.this, "Please enter valid visitor name.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if we get the data then we are setting it in our text views in below line.
                        VisitorID = jsonObject.getInt("visitorID");
                        etVisitorName.setText(jsonObject.getString("visitorName"));
                        etPhoneNumber.setText(Integer.toString(jsonObject.getInt("phoneNumber")));
                        etCheckInDate.setText(jsonObject.getString("checkInDate"));
                        VehicleType = jsonObject.getString("vehicleType");
                        etCheckOutDate.setText(jsonObject.getString("checkOutDate"));
                        etNoOfVisitors.setText(Integer.toString(jsonObject.getInt("noOfVisitors")));
                        etPlateNumber.setText(jsonObject.getString("plateNumber"));
                        etLicenceNumber.setText(jsonObject.getString("licenceNumber"));

                        vehicleTypeSpin.setSelection(getPosition(vehicleTypeSpin,VehicleType));


                    }
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(EditVisitor.this, "Fail to get course" + error, Toast.LENGTH_SHORT).show();
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

                // below line we are creating a map for storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key and value pair to our parameters.
                params.put("visitorName", visitorName);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    private int getPosition(Spinner vehicleTypeSpin, String vehicleType) {

        for(int i=0;i<vehicleTypeSpin.getCount();i++){
            if(vehicleType.equals(vehicleTypeSpin.getItemAtPosition(i))){
                return i;
            }
        }
        return 0;
    }


    public void fnUpdate(int visitorID, String VisitorName, int PhoneNumber, String CheckInDate, String CheckOutDate, int NoOfVisitors, String VehicleType, String PlateNumber, String LicenceNumber, String ParkingNumber, String approveParking) {

        Intent intentSuccess = new Intent(EditVisitor.this, SuccessFullyUpdate.class);
        startActivity(intentSuccess);





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditVisitor.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditVisitor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("visitorID", String.valueOf(VisitorID));
                params.put("visitorName", VisitorName);
                params.put("phoneNumber", String.valueOf(PhoneNumber));
                params.put("checkInDate", CheckInDate);
                params.put("checkOutDate", CheckOutDate);
                params.put("noOfVisitors", String.valueOf(NoOfVisitors));
                params.put("vehicleType", VehicleType);
                params.put("plateNumber", PlateNumber);
                params.put("licenceNumber", LicenceNumber);
                params.put("parkingNumber", ParkingNumber);
                params.put("approveParking",ApproveParking);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditVisitor.this);
        requestQueue.add(request);
    }

    public void fnBackEdit(View view) {
        Intent intentEdit = new Intent(EditVisitor.this, ViewVisitor.class);
        startActivity(intentEdit);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        VehicleType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}