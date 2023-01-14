package hajarshaufi.parcel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApproveVisitorParking extends AppCompatActivity {

    Button btnMotor1, btnMotor2, btnMotor3, btnMotor4, btnMotor5, btnCar1, btnCar2, btnCar3, btnCar4, btnCar5, btnCar6, btnCar7, btnCar8, btnCar9, btnCar10, btnEditParking;
    TextView txtParkingLot, VehicleTypeTxt;
    Visitor visitor;
    List<Visitor> visitorList;
    List<String> ParkingLot = new ArrayList<>();
    private String VisitorName, VehicleType ,ParkingNumber,ApproveParking;

    String ApproveStatus="Approved";
    String url = "http://192.168.146.86/condoapp/viewVisitor.php";
    String url1 = "http://1192.168.146.86/condoapp/getParkingDetails.php";
    String url2 = "http://192.168.146.86/condoapp/Approve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_visitor_parking);
        txtParkingLot = findViewById(R.id.txtParkingLot);
        VehicleTypeTxt = findViewById(R.id.VehicleTypeTxt);

        btnEditParking = findViewById(R.id.btnEditParking);
        btnMotor2 = findViewById(R.id.btnMotor2);
        btnMotor3 = findViewById(R.id.btnMotor3);
        btnMotor4 = findViewById(R.id.btnMotor4);
        btnMotor5 = findViewById(R.id.btnMotor5);
        btnCar1 = findViewById(R.id.btnCar1);
        btnCar2 = findViewById(R.id.btnCar2);
        btnCar3 = findViewById(R.id.btnCar3);
        btnCar4 = findViewById(R.id.btnCar4);
        btnCar5 = findViewById(R.id.btnCar5);
        btnCar6 = findViewById(R.id.btnCar6);
        btnCar7 = findViewById(R.id.btnCar7);
        btnCar8 = findViewById(R.id.btnCar8);
        btnCar9 = findViewById(R.id.btnCar9);
        btnCar10 = findViewById(R.id.btnCar10);

        btnEditParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParkingNumber = txtParkingLot.getText().toString();
                ApproveParking=ApproveStatus;

                fnUpdate(VisitorName,ParkingNumber,ApproveParking);

                Intent intentSuccess = new Intent(ApproveVisitorParking.this, Approve.class);
                startActivity(intentSuccess);
            }
        });


        if (getIntent() != null) {
            visitor = (Visitor) getIntent().getSerializableExtra("visitor");

            VisitorName = visitor.getVisitorName();

        }

        visitorList =  new ArrayList<>();

        getParkingDetails();


        getVisitorInformation(VisitorName);

    }

    public void getVisitorInformation(String visitorName) {


        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ApproveVisitorParking.this);

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
                        Toast.makeText(ApproveVisitorParking.this, "Please enter valid visitor name.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if we get the data then we are setting it in our text views in below line.
                        VehicleType = jsonObject.getString("vehicleType");
                        VehicleTypeTxt.setText(VehicleType);


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
                Toast.makeText(ApproveVisitorParking.this, "Fail to get course" + error, Toast.LENGTH_SHORT).show();
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

    public void getParkingDetails() {


        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        JsonArrayRequest request = new JsonArrayRequest(url1,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray array) {
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject jsonObject = array.getJSONObject(i);
                        // if we get the data then we are setting it in our text views in below line.


                        String ParkingNumber = jsonObject.getString("parkingNumber").trim();

                        ParkingLot.add(ParkingNumber);
                        boolean motor1 = ParkingLot.contains("A1 M100");
                        if (motor1){
                            Button mo1;
                            mo1=findViewById(R.id.btnMotor1);
                            mo1.setEnabled(false);
                        }
                        boolean motor2 = ParkingLot.contains("A1 M101");
                        if (motor2){
                            btnMotor2.setEnabled(false);
                        }
                        boolean motor3 = ParkingLot.contains("A1 M102");
                        if (motor3){
                            btnMotor3.setEnabled(false);
                        }
                        boolean motor4 = ParkingLot.contains("A1 M103");
                        if (motor4){
                            btnMotor4.setEnabled(false);
                        }
                        boolean motor5 = ParkingLot.contains("A1 M104");
                        if (motor5){
                            btnMotor5.setEnabled(false);
                        }
                        boolean car1 = ParkingLot.contains("A1 C100");
                        if (car1){
                            btnCar1.setEnabled(false);
                        }
                        boolean car2 = ParkingLot.contains("A1 C101");
                        if (car2){
                            btnCar2.setEnabled(false);
                        }
                        boolean car3 = ParkingLot.contains("A1 C102");
                        if (car3){
                            btnCar3.setEnabled(false);
                        }
                        boolean car4 = ParkingLot.contains("A1 C103");
                        if (car4){
                            btnCar4.setEnabled(false);
                        }
                        boolean car5 = ParkingLot.contains("A1 C104");
                        if (car5){
                            btnCar5.setEnabled(false);
                        }
                        boolean car6 = ParkingLot.contains("A1 C105");
                        if (car6){
                            btnCar6.setEnabled(false);
                        }
                        boolean car7 = ParkingLot.contains("A1 C106");
                        if (car7){
                            btnCar7.setEnabled(false);
                        }
                        boolean car8 = ParkingLot.contains("A1 C107");
                        if (car8){
                            btnCar8.setEnabled(false);
                        }
                        boolean car9 = ParkingLot.contains("A1 C108");
                        if (car9){
                            btnCar9.setEnabled(false);
                        }
                        boolean car10 = ParkingLot.contains("A1 C109");
                        if (car10){
                            btnCar10.setEnabled(false);
                        }



                        // on below line we are displaying
                        // a success toast message.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // method to handle errors.
                Toast.makeText(ApproveVisitorParking.this, "Fail to get visitor " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        // below line is to make
        // a json object request.
        RequestQueue requestQueue=Volley.newRequestQueue(ApproveVisitorParking.this);
        requestQueue.add(request);

    }

    public void fnUpdate( String visitorName , String ParkingNumber, String ApproveParking) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ApproveVisitorParking.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ApproveVisitorParking.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(ApproveVisitorParking.this);
        requestQueue.add(request);
    }

    public void fnMotor1(View view) {
        Button M1;
        txtParkingLot.setText("A1 M100");
        M1 = findViewById(R.id.btnMotor1);
        M1.setEnabled(false);
    }

    public void fnMotor3(View view) {
        Button M3;
        txtParkingLot.setText("A1 M102");
        M3 = findViewById(R.id.btnMotor3);
        M3.setEnabled(false);
    }

    public void fnCar4(View view) {
        Button C4;
        txtParkingLot.setText("A1 C103");
        C4 = findViewById(R.id.btnCar4);
        C4.setEnabled(false);
    }

    public void fnCar9(View view) {
        Button C9;
        txtParkingLot.setText("A1 C109");
        C9 = findViewById(R.id.btnCar9);
        C9.setEnabled(false);
    }


    public void fnCar8(View view) {
        Button C8;
        txtParkingLot.setText("A1 C107");
        C8 = findViewById(R.id.btnCar8);
        C8.setEnabled(false);
    }

    public void fnCar10(View view) {
        Button C10;
        txtParkingLot.setText("A1 C108");
        C10 = findViewById(R.id.btnCar10);
        C10.setEnabled(false);
    }


    public void fnCar5(View view) {
        Button C5;
        txtParkingLot.setText("A1 C104");
        C5 = findViewById(R.id.btnCar5);
        C5.setEnabled(false);
    }

    public void fnCar7(View view) {
        Button C7;
        txtParkingLot.setText("A1 C106");
        C7 = findViewById(R.id.btnCar7);
        C7.setEnabled(false);
    }

    public void fnCar6(View view) {
        Button C6;
        txtParkingLot.setText("A1 C105");
        C6 = findViewById(R.id.btnCar6);
        C6.setEnabled(false);
    }

    public void fnCar2(View view) {
        Button C2;
        txtParkingLot.setText("A1 C101");
        C2 = findViewById(R.id.btnCar2);
        C2.setEnabled(false);
    }


    public void fnCar1(View view) {
        Button C1;
        txtParkingLot.setText("A1 C100");
        C1 = findViewById(R.id.btnCar1);
        C1.setEnabled(false);
    }

    public void fnCar3(View view) {
        Button C3;
        txtParkingLot.setText("A1 C102");
        C3 = findViewById(R.id.btnCar3);
        C3.setEnabled(false);
    }

    public void fnMotor4(View view) {
        Button M4;
        txtParkingLot.setText("A1 M103");
        M4 = findViewById(R.id.btnMotor4);
        M4.setEnabled(false);
    }


    public void fnMotor5(View view) {
        Button M5;
        txtParkingLot.setText("A1 M104");
        M5 = findViewById(R.id.btnMotor5);
        M5.setEnabled(false);
    }


    public void fnMotor2(View view) {
        Button M2;
        txtParkingLot.setText("A1 M101");
        M2 = findViewById(R.id.btnMotor2);
        M2.setEnabled(false);
    }

    public void fnBackParking(View view) {
        Intent intentSuccess = new Intent(ApproveVisitorParking.this, ViewVisitorManagement.class);
        startActivity(intentSuccess);
    }
}