package hajarshaufi.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewVisitorManagement extends AppCompatActivity {

    RecyclerView ViewVisitorManagement;
    List<Visitor> visitorList;

    ViewVisitorManagementAdapter adapterManagement;
    String url = "http://192.168.1.113/condoapp/getViewVisitorManagement.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visitor_management);
        ViewVisitorManagement =findViewById(R.id.ViewVisitorManagement);
        ViewVisitorManagement.setHasFixedSize(true);
        ViewVisitorManagement.setLayoutManager(new LinearLayoutManager(this));

        visitorList =  new ArrayList<>();

        getVisitorsDetails();
    }

    public void getVisitorsDetails(){


        // url to post our data
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        JsonArrayRequest requestArray = new JsonArrayRequest(url,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray arrayList) {
                for (int i = 0; i < arrayList.length(); i++) {
                    try {
                        JSONObject Object = arrayList.getJSONObject(i);
                        // if we get the data then we are setting it in our text views in below line.

                        String VisitorName = Object.getString("visitorName").trim();
                        int PhoneNumber = Object.getInt("phoneNumber");
                        String CheckInDate = Object.getString("checkInDate").trim();
                        String CheckOutDate = Object.getString("checkOutDate").trim();
                        String ParkingNumber = Object.getString("parkingNumber").trim();
                        String ApproveStatus = Object.getString("approveParking").trim();


                        Visitor visitor = new Visitor(VisitorName, PhoneNumber, CheckInDate, CheckOutDate, ParkingNumber,ApproveStatus);
                        visitorList.add(visitor);
                        // on below line we are displaying
                        // a success toast message.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapterManagement = new ViewVisitorManagementAdapter(ViewVisitorManagement.this, visitorList);
                ViewVisitorManagement.setAdapter(adapterManagement);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // method to handle errors.
                Toast.makeText(ViewVisitorManagement.this, "Fail to get visitor " + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue= Volley.newRequestQueue(ViewVisitorManagement.this);
        requestQueue.add(requestArray);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ViewVisitorManagement.this, StaffHomeScreenActivity.class);
        startActivity(intent);
    }
}