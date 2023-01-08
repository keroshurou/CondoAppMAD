package hajarshaufi.parcel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParcelListView extends AppCompatActivity {

    ImageButton btnBack;

    ListView listView;
    ParcelAdapter parcelAdapter;
    public static ArrayList<Parcel> parcelArrayList = new ArrayList<>();
    String url = "http://192.168.43.225/condoapp/fetchDataParcel.php";
    String url1 = "http://192.168.43.225/condoapp/deleteParcel.php";
    String url3 = "http://192.168.43.225/condoapp/searchParcel.php";
    Parcel parcel;
    ImageButton btnSearch;
    EditText edtSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_list_view);


        listView = findViewById(R.id.parcelListView);
        parcelAdapter = new ParcelAdapter(this, parcelArrayList);
        listView.setAdapter(parcelAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long parcelID) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Parcel","Edit Parcel","Delete Parcel"};
                builder.setTitle(parcelArrayList.get(position).getTrackingNumber());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetailParcel.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditParcelDetails.class)
                                        .putExtra("position",position));
                                break;
                            case 2:
                                startActivity(new Intent(getApplicationContext(),DeleteParcel.class)
                                        .putExtra("position",position));
                                //deleteData(parcelArrayList.get(position).getParcelID());
                                break;
                        }

                    }
                });

                builder.create().show();
            }
        });

        //Get all Id's
        btnBack = findViewById(R.id.btnBack);

        //Intent to Parcel Management Menu
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(ParcelListView.this,
                        ParcelManagementMain.class);
                startActivity(intentBack);
            }
        });

        getData();

        edtSearchBar = findViewById(R.id.searchBar);
        btnSearch = findViewById(R.id.imageButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtSearchBar.getText().toString())) {
                    Toast.makeText(ParcelListView.this, "Please enter course id", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling method to load data.
                getSearchData(edtSearchBar.getText().toString());
            }
        });
    }

    public void getData() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parcelArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("item");

                    if(success.equals("1")){

                        for(int i=0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String parcelID = object.getString("parcelID");
                            String collectorName = object.getString("collectorName");
                            String parcelUnit = object.getString("parcelUnit");
                            String expressBrand = object.getString("expressBrand");
                            String trackingNumber = object.getString("trackingNumber");
                            String deliveredDate = object.getString("deliveredDate");
                            String collectStatus = object.getString("collectStatus");
                            String collectedDate = object.getString("collectedDate");

                            parcel = new Parcel(parcelID, collectorName, parcelUnit, expressBrand,
                                    trackingNumber, deliveredDate, collectStatus, collectedDate );
                            parcelArrayList.add(parcel);
                            parcelAdapter.notifyDataSetChanged();
                        }
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParcelListView.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ParcelListView.this);
        requestQueue.add(request);
    }

    private void getSearchData(String collectorName) {

        // url to post our data
        String url = "http://localhost/courseApp/searchParcel.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ParcelListView.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are checking if the response is null or not.
                    if (jsonObject.getString("collectorName") == null) {
                        // displaying a toast message if we get error
                        Toast.makeText(ParcelListView.this, "Please enter valid name.", Toast.LENGTH_SHORT).show();
                    } else {
                        parcelArrayList.clear();
                        try {
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            if(success.equals("1")){

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String parcelID = object.getString("parcelID");
                                    String collectorName = object.getString("collectorName");
                                    String parcelUnit = object.getString("parcelUnit");
                                    String expressBrand = object.getString("expressBrand");
                                    String trackingNumber = object.getString("trackingNumber");
                                    String deliveredDate = object.getString("deliveredDate");
                                    String collectStatus = object.getString("collectStatus");
                                    String collectedDate = object.getString("collectedDate");

                                    parcel = new Parcel(parcelID, collectorName, parcelUnit, expressBrand,
                                            trackingNumber, deliveredDate, collectStatus, collectedDate );
                                    parcelArrayList.add(parcel);
                                    parcelAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(ParcelListView.this, "Fail to get parcel" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key and value pair to our parameters.
                params.put("collectorName", collectorName);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

        RequestQueue requestQueue = Volley.newRequestQueue(ParcelListView.this);
        requestQueue.add(request);
    }

    private void deleteData(final String parcelID) {

        StringRequest request = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(ParcelListView.this, "Data Deleted Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ParcelListView.this, "Data Not Deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParcelListView.this, error.getMessage(),Toast.LENGTH_SHORT).show();
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

        Intent intent = new Intent(ParcelListView.this, ParcelListView.class);
        startActivity(intent);
    }

}