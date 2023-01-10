package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParcelView extends AppCompatActivity {

    private EditText searchBarEdt;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_view);

        //Get all IDs
        EditText searchBarEdt = findViewById(R.id.editSearchBar);
        TextView textView = findViewById(R.id.textView);
        Button btnFetch = findViewById(R.id.btnFetchData);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = searchBarEdt.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.43.255/condoapp/getAllParcel.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String parcelID = jsonObject.getString("parcelID");
                                        String managementID = jsonObject.getString("managementID");
                                        String collectorName = jsonObject.getString("collectorName");
                                        String parcelUnit = jsonObject.getString("parcelUnit");
                                        String expressBrand = jsonObject.getString("expressBrand");
                                        String trackingNumber = jsonObject.getString("trackingNumber");
                                        String deliveredDate = jsonObject.getString("deliveredDate");
                                        String collectStatus = jsonObject.getString("collectStatus");
                                        String collectedDate = jsonObject.getString("collectedDate");
                                        textView.append("ParcelID - " +parcelID+ "\nManagement ID - " + managementID + "\nCollector Name - " + collectorName + "\nParcel Unit - "
                                                + parcelUnit + "\nExpress Brand - " + expressBrand + "\nTracking Number - " + trackingNumber + "\nDelivered Date - "
                                                + deliveredDate + "\nCollect Status - " + collectStatus + "\nCollected Date - " + collectedDate + "\n\n");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getLocalizedMessage());
                    }
                }){
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();

                        params.put("collectorName", String.valueOf(searchBarEdt));

                        // at last we are returning our params.
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
/***
    public void searchAll (View view){
        String data = searchBarEdt.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.56.1/condoapp/getAllParcel.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String managementID = jsonObject.getString("managementID");
                                String collectorName = jsonObject.getString("collectorName");
                                String parcelUnit = jsonObject.getString("parcelUnit");
                                String expressBrand = jsonObject.getString("expressBrand");
                                String trackingNumber = jsonObject.getString("trackingNumber");
                                String deliveredDate = jsonObject.getString("deliveredDate");
                                String collectStatus = jsonObject.getString("collectStatus");
                                String collectedDate = jsonObject.getString("collectedDate");
                                textView.append("Management ID - " + managementID + "\nCollector Name - " + collectorName + "\nParcel Unit - "
                                        + parcelUnit + "\nExpress Brand - " + expressBrand + "\nTracking Number - " + trackingNumber + "\nDelivered Date - "
                                        + deliveredDate + "\nCollect Status - " + collectStatus + "\nCollected Date - " + collectedDate + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getLocalizedMessage());
            }
        }){
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("collectorName", String.valueOf(searchBarEdt));

                // at last we are returning our params.
                return params;
            }
        };
        queue.add(stringRequest);
    }***/
}