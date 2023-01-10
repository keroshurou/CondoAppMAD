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

import hajarshaufi.parcel.databinding.ActivityDialogueBinding;
import hajarshaufi.parcel.databinding.ActivityDialogueCancelBinding;
import hajarshaufi.parcel.databinding.ActivityForgotPassBinding;

public class ForgotPassActivity extends AppCompatActivity {
    private ActivityForgotPassBinding binding;
    private ActivityDialogueBinding dialogueBinding;
    private ActivityDialogueCancelBinding cancelBinding;
    String url = "http://192.168.43.255/condoapp/forgot.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPassBinding.inflate(getLayoutInflater());
        dialogueBinding = ActivityDialogueBinding.inflate(getLayoutInflater());
        cancelBinding = ActivityDialogueCancelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("data");

        hooks();

        binding.sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtEmail.getText().toString().trim();

                ProgressDialog mProgressDialog =new ProgressDialog(ForgotPassActivity.this);
                mProgressDialog.setTitle("Wait");
                mProgressDialog.setMessage("Mail sending...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.show();

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    if (object.get("code").equals("100")) {
                                        mProgressDialog.dismiss();
                                        setContentView(dialogueBinding.getRoot());
                                        dialogueBinding.textView2.setText("e-mail has been sent check your mailbox!");
//                                        startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
//                                        finish();
                                        // Toast.makeText(ForgotPassActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                    if (object.get("code").equals("101")) {
                                        setContentView(cancelBinding.getRoot());
                                        cancelBinding.textView2.setText(object.getString("msg"));
                                        mProgressDialog.dismiss();
                                        // Toast.makeText(ForgotPassActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                    if (object.get("code").equals("102")) {
                                        setContentView(cancelBinding.getRoot());
                                        cancelBinding.textView2.setText("email not exist!");
                                        mProgressDialog.dismiss();
                                        //  Toast.makeText(ForgotPassActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ForgotPassActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(ForgotPassActivity.this);
                queue.add(request);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.goToForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("user")){
                    Intent i = new Intent(ForgotPassActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(ForgotPassActivity.this,StaffLoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private void hooks() {
        cancelBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(binding.getRoot());
            }
        });

        cancelBinding.btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dialogueBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(binding.getRoot());
            }
        });

        dialogueBinding.btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}