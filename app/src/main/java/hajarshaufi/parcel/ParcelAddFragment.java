package hajarshaufi.parcel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParcelAddFragment extends Fragment {

    //Declare EditText & Button
    private EditText collectorNameEdt, parcelUnitEdt, expressBrandEdt, trackingNumberEdt,
            deliveredDateEdt;

    Button submitAddParcel;

    private String collectorName, parcelUnit, expressBrand, trackingNumber,
            deliveredDate, collectStatus;

    private DatePickerDialog datePicker;
    private Spinner courierSpinner;

    String availableStatus = "Available";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parcel_add, container, false);

        //Get all IDs
        collectorNameEdt = view.findViewById(R.id.edtCollectorName);
        parcelUnitEdt = view.findViewById(R.id.edtUnitNumber);
        trackingNumberEdt = view.findViewById(R.id.edtTrackingNumber);
        deliveredDateEdt = view.findViewById(R.id.edtDeliveredDate);

        courierSpinner = view.findViewById(R.id.courierSpinner);
        submitAddParcel = view.findViewById(R.id.addBtn);

        submitAddParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getParcel();

                Intent intentSuccess = new Intent(getContext(),
                        ParcelAddSuccess.class);
                startActivity(intentSuccess);
            }
        });

        deliveredDateEdt.setOnTouchListener(new View.OnTouchListener() {
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
                    datePicker = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    deliveredDateEdt.setText(year  + "-" + (monthOfYear + 1)
                                            + "-" + dayOfMonth);
                                }
                            }, year,month, day);
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePicker.show();
                }
                return false;
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    public void getParcel(){

        collectorName = collectorNameEdt.getText().toString();
        parcelUnit = parcelUnitEdt.getText().toString();
        trackingNumber = trackingNumberEdt.getText().toString();
        expressBrand = courierSpinner.getSelectedItem().toString().trim();
        deliveredDate = deliveredDateEdt.getText().toString();
        collectStatus = availableStatus;

        if (TextUtils.isEmpty(collectorName)){
            collectorNameEdt.setError("Please enter Collector Name");
        } else if(TextUtils.isEmpty(parcelUnit)){
            parcelUnitEdt.setError("Please enter House Unit Number");
        } /**else if(TextUtils.isEmpty(expressBrand)) {
         expressBrandEdt.setError("Please enter Express Brand (Courier Service)");
         } **/else if(TextUtils.isEmpty(trackingNumber)) {
            trackingNumberEdt.setError("Please enter Tracking Number");
        } else if(TextUtils.isEmpty(deliveredDate)) {
            deliveredDateEdt.setError("Please enter Delivered Date");
        } else {
            addDataToDatabase(collectorName, parcelUnit, expressBrand, trackingNumber,
                    deliveredDate, collectStatus);
        }
    }

    private void addDataToDatabase(String collectorName, String parcelUnit, String expressBrand,
                                   String trackingNumber, String deliveredDate,
                                   String collectStatus) {

        // url to post our data
        String url = "http://192.168.0.16/condoapp/insertParcel.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getContext());

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
                    Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                collectorNameEdt.setText("");
                parcelUnitEdt.setText("");
                expressBrandEdt.setText("");
                trackingNumberEdt.setText("");
                deliveredDateEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("collectorName", collectorName);
                params.put("parcelUnit", parcelUnit);
                params.put("trackingNumber", trackingNumber);
                params.put("expressBrand", expressBrand);
                params.put("deliveredDate", deliveredDate);
                params.put("collectStatus", collectStatus);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

        Intent intentSuccess = new Intent(getContext(), ParcelAddSuccess.class);
        startActivity(intentSuccess);
    }


}