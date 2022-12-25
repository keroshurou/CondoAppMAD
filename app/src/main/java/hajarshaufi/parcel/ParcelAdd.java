package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParcelAdd extends AppCompatActivity {

    //Declare EditText & Button
    private EditText managementIDEdt, collectorNameEdt, parcelUnitEdt, expressBrandEdt, trackingNumberEdt,
            deliveredDateEdt, collectStatusEdt, collectedDateEdt;

    Button submitAddParcel;
    private String collectorName, parcelUnit, expressBrand, trackingNumber,
            deliveredDate, collectStatus;
    private int managementID;

    String availableStatus = "Available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_add);

        //Get all IDs
        managementIDEdt = findViewById(R.id.edtMangID);
        collectorNameEdt = findViewById(R.id.edtCollectorName);
        parcelUnitEdt = findViewById(R.id.edtUnitNumber);
        expressBrandEdt = findViewById(R.id.edtExpressBrand);
        trackingNumberEdt = findViewById(R.id.edtTrackingNumber);
        deliveredDateEdt = findViewById(R.id.edtDeliveredDate);
        /**collectStatusEdt = findViewById(R.id.edtCollectStatus);
        collectedDateEdt = findViewById(R.id.edtCollectedDate);**/
        submitAddParcel = findViewById(R.id.addBtn);

        submitAddParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParcel();

                Intent intentSuccess = new Intent(ParcelAdd.this, ParcelSuccess.class);
                startActivity(intentSuccess);
            }
        });
    }

    public void getParcel(){

        managementID = Integer.parseInt(managementIDEdt.getText().toString());
        collectorName = collectorNameEdt.getText().toString();
        parcelUnit = parcelUnitEdt.getText().toString();
        expressBrand = expressBrandEdt.getText().toString();
        trackingNumber = trackingNumberEdt.getText().toString();
        deliveredDate = deliveredDateEdt.getText().toString();
        collectStatus = availableStatus;
        /**collectStatus = collectStatusEdt.getText().toString();
        collectedDate = collectedDateEdt.getText().toString();**/

        if (TextUtils.isEmpty(collectorName)){
            collectorNameEdt.setError("Please enter Collector Name");
        } else if(TextUtils.isEmpty(parcelUnit)){
            parcelUnitEdt.setError("Please enter House Unit Number");
        } else if(TextUtils.isEmpty(expressBrand)) {
            expressBrandEdt.setError("Please enter Express Brand (Courier Service)");
        } else if(TextUtils.isEmpty(trackingNumber)) {
            trackingNumberEdt.setError("Please enter Tracking Number");
        } else if(TextUtils.isEmpty(deliveredDate)) {
            deliveredDateEdt.setError("Please enter Delivered Date");
        } /**else if(TextUtils.isEmpty(collectStatus)) {
            collectStatusEdt.setError("Please enter Collect Status");
        } else if(TextUtils.isEmpty(collectedDate)) {
            collectedDateEdt.setError("Please enter Collected Date");
        } **/else {
            addDataToDatabase(Integer.parseInt(String.valueOf(managementID)), collectorName, parcelUnit, expressBrand, trackingNumber,
                    deliveredDate, collectStatus);
        }
    }

    private void addDataToDatabase(int managementID, String collectorName, String parcelUnit, String expressBrand,
                                   String trackingNumber, String deliveredDate,
                                   String collectStatus) {

        // url to post our data
        String url = "http://192.168.56.101/condoapp/addParcel.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ParcelAdd.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(ParcelAdd.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                managementIDEdt.setText("");
                collectorNameEdt.setText("");
                parcelUnitEdt.setText("");
                expressBrandEdt.setText("");
                trackingNumberEdt.setText("");
                deliveredDateEdt.setText("");
                collectStatusEdt.setText("");
                collectedDateEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(ParcelAdd.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("managementID", String.valueOf(managementID));
                params.put("collectorName", collectorName);
                params.put("parcelUnit", parcelUnit);
                params.put("expressBrand", expressBrand);
                params.put("trackingNumber", trackingNumber);
                params.put("deliveredDate", deliveredDate);
                params.put("collectStatus", collectStatus);
                //params.put("collectedDate", collectedDate);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}