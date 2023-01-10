package hajarshaufi.parcel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


public class ParcelViewFragment extends Fragment {

    ImageButton btnBack;

    ListView listView;
    ParcelAdapter parcelAdapter;
    public static ArrayList<Parcel> parcelArrayList = new ArrayList<>();
    String url = "http://192.168.0.16/condoapp/fetchDataParcel.php";
    String url1 = "http://192.168.0.16/condoapp/deleteParcel.php";
    Parcel parcel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parcel_view, container, false);

        listView = view.findViewById(R.id.parcelListView);
        parcelAdapter = new ParcelAdapter(getContext(), parcelArrayList);
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
                                startActivity(new Intent(getContext(),DetailParcel.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getContext(),EditParcelDetails.class)
                                        .putExtra("position",position));
                                break;
                            case 2:
                                startActivity(new Intent(getContext(),DeleteParcel.class)
                                        .putExtra("position",position));
                                //deleteData(parcelArrayList.get(position).getParcelID());
                                break;
                        }

                    }
                });

                builder.create().show();
            }
        });

        getData();

        // Inflate the layout for this fragment
        return view;
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
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void deleteData(final String parcelID) {

        StringRequest request = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equalsIgnoreCase("Data Deleted")){
                    Toast.makeText(getContext(), "Data Deleted Successfully",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Data Not Deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("parcelID", parcelID);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

        Intent intent = new Intent(getContext(), ParcelListView.class);
        startActivity(intent);
    }

}