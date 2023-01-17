package hajarshaufi.parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import hajarshaufi.parcel.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    String url = "http://192.168.26.86/condoapp/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mProgressDialog =new ProgressDialog(RegisterActivity.this);
                mProgressDialog.setTitle("Wait");
                mProgressDialog.setMessage("User Registration");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.show();
                String uname = binding.edtUsername.getText().toString().trim();
                String email = binding.regEmail.getText().toString().trim();
                String pass = binding.edtPassword.getText().toString().trim();
                String uno = binding.regUnit.getText().toString().trim();

                if (uno.isEmpty() && email.isEmpty() && pass.isEmpty() && uno.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Some Fields are empty?", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(RegisterActivity.this, "Enter Valid Email?", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        if (object.get("code").equals("201")) {
                                            mProgressDialog.dismiss();
                                            Intent intent = new Intent(RegisterActivity.this, RegisterCompletedActivity.class);
                                            intent.putExtra("type","user");
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.get("code").equals("202")) {
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.get("code").equals("200")) {
                                            mProgressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        mProgressDialog.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mProgressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", uname);
                            params.put("email", email);
                            params.put("password", pass);
                            params.put("unitno", uno);
                            return params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(request);
                }

            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}