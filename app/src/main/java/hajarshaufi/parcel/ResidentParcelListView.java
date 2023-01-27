package hajarshaufi.parcel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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

public class ResidentParcelListView extends AppCompatActivity {

    ImageButton btnBack;

    ListView residentListView;
    ParcelAdapter residentParcelAdapter;
    public static ArrayList<Parcel> residentParcelArrayList = new ArrayList<>();
    String url = "http://192.168.59.86/condoapp/fetchDataParcelResident.php";
    Parcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_parcel_list_view);

        residentListView = findViewById(R.id.residentParcelListView);
        residentParcelAdapter = new ParcelAdapter(this, residentParcelArrayList);
        residentListView.setAdapter(residentParcelAdapter);

        residentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long parcelID) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Parcel"};
                builder.setTitle(residentParcelArrayList.get(position).getTrackingNumber());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(),ResidentDetailParcel.class)
                                        .putExtra("position",position));
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
                Intent intentBack = new Intent(ResidentParcelListView.this,
                        MainActivity.class);
                startActivity(intentBack);
            }
        });

        getData();
    }

    public void getData() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                residentParcelArrayList.clear();
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
                            residentParcelArrayList.add(parcel);
                            residentParcelAdapter.notifyDataSetChanged();
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
                Toast.makeText(ResidentParcelListView.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ResidentParcelListView.this);
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResidentParcelListView.this, MainActivity.class);
        startActivity(intent);
    }
}

