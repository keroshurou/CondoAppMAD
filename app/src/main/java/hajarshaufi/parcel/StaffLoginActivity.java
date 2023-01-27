package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import hajarshaufi.parcel.databinding.ActivityStaffLoginBinding;

public class StaffLoginActivity extends AppCompatActivity {

    private ActivityStaffLoginBinding binding;
    String url = "http://192.168.1.113/condoapp/stafflogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        binding = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.staffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.staffUsername.getText().toString().trim();
                String password = binding.staffPassword.getText().toString().trim();

                ProgressDialog mProgressDialog =new ProgressDialog(StaffLoginActivity.this);
                mProgressDialog.setTitle("Wait");
                mProgressDialog.setMessage("Staff Login");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.show();

                if (username.isEmpty() && password.isEmpty()){
                    Toast.makeText(StaffLoginActivity.this, "Some Fields are empty?", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        if (object.get("code").equals("200")){
                                            mProgressDialog.dismiss();
                                            startActivity(new Intent(StaffLoginActivity.this, StaffHomeScreenActivity.class));
                                            finish();
                                            Toast.makeText(StaffLoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }else if (object.get("code").equals("202")){
                                            mProgressDialog.dismiss();
                                            Toast.makeText(StaffLoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        mProgressDialog.dismiss();
                                        Toast.makeText(StaffLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mProgressDialog.dismiss();
                            Toast.makeText(StaffLoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(StaffLoginActivity.this);
                    queue.add(request);
                }
            }
        });

        /*binding.staffForgotpassTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StaffLoginActivity.this,ForgotPassActivity.class);
                i.putExtra("data","staff");
                startActivity(i);
            }
        });*/

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StaffLoginActivity.this,OnBoardingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}