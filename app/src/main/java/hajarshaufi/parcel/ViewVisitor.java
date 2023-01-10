package hajarshaufi.parcel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class ViewVisitor extends AppCompatActivity {


    RecyclerView ViewVisitor;

    VisitorAdapter adapter;
    ProgressBar progress;
    // creating variable for button
    private Button btnSearch;
    List<Visitor> filterList=new ArrayList<>();
    List<Visitor> visitorList;

    EditText edtSearch;
    String url = "http://192.168.43.255/condoapp/getVisitor.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visitor);
        // initializing all our variables.
        edtSearch = findViewById(R.id.edtSearch);
        ViewVisitor =findViewById(R.id.ViewVisitor);
        ViewVisitor.setHasFixedSize(true);
        ViewVisitor.setLayoutManager(new LinearLayoutManager(this));
        progress=findViewById(R.id.progress);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filterList.clear();
                if(s.toString().isEmpty()){
                    ViewVisitor.setAdapter(new VisitorAdapter(getApplicationContext(),visitorList));
                    adapter.notifyDataSetChanged();
                }else {
                    Filter(s.toString());
                }

            }
        });
        visitorList =  new ArrayList<>();

        getVisitorDetails();

    }

    private void Filter(String text) {
        for(Visitor visitor:visitorList){
            if(visitor.getVisitorName().equals(text)){
                filterList.add(visitor);
            }
        }
        ViewVisitor.setAdapter(new VisitorAdapter(getApplicationContext(),filterList));
        adapter.notifyDataSetChanged();
    }

    public void fnBack(View view) {
        Intent intent = new Intent(getApplicationContext(), mainPage.class);
        startActivity(intent);
    }



    public void getVisitorDetails(){


        // url to post our data
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        JsonArrayRequest request = new JsonArrayRequest(url,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray array) {
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject jsonObject = array.getJSONObject(i);
                        // if we get the data then we are setting it in our text views in below line.

                        String VisitorName = jsonObject.getString("visitorName").trim();
                        int PhoneNumber = jsonObject.getInt("phoneNumber");
                        String CheckInDate = jsonObject.getString("checkInDate").trim();
                        String CheckOutDate = jsonObject.getString("checkOutDate").trim();
                        String ParkingNumber = jsonObject.getString("parkingNumber").trim();
                        String ApproveStatus = jsonObject.getString("approveParking").trim();

                        Visitor visitor = new Visitor(VisitorName, PhoneNumber, CheckInDate, CheckOutDate, ParkingNumber,ApproveStatus);
                        visitorList.add(visitor);
                        // on below line we are displaying
                        // a success toast message.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progress.setVisibility(View.GONE);
                adapter = new VisitorAdapter(ViewVisitor.this, visitorList);
                ViewVisitor.setAdapter(adapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // method to handle errors.
                Toast.makeText(ViewVisitor.this, "Fail to get visitor " + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue=Volley.newRequestQueue(ViewVisitor.this);
        requestQueue.add(request);
    }

}

