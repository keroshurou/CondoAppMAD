package hajarshaufi.parcel.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import hajarshaufi.parcel.ForgotPassActivity;
import hajarshaufi.parcel.LoginActivity;
import hajarshaufi.parcel.MainActivity;
import hajarshaufi.parcel.OnBoardingActivity;
import hajarshaufi.parcel.R;
import hajarshaufi.parcel.RegisterActivity;
import hajarshaufi.parcel.databinding.FragmentResidentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Resident#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Resident extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Resident() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Resident.
     */
    // TODO: Rename and change types and number of parameters
    public static Resident newInstance(String param1, String param2) {
        Resident fragment = new Resident();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FragmentResidentBinding binding;
    String url = "http://192.168.1.113/condoapp/login.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_get_joke_activity, container, false);
        binding = FragmentResidentBinding.inflate(inflater, container, false);
        binding.signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUsername.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();

                ProgressDialog mProgressDialog =new ProgressDialog(view.getContext());
                mProgressDialog.setTitle("Wait");
                mProgressDialog.setMessage("Residents");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.show();

                if (username.isEmpty() && password.isEmpty()){
                    Toast.makeText(view.getContext(), "Some Fields are empty?", Toast.LENGTH_SHORT).show();
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
                                            startActivity(new Intent(view.getContext(), MainActivity.class));
                                            getActivity().finish();
                                            Toast.makeText(view.getContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }else if (object.get("code").equals("202")){
                                            mProgressDialog.dismiss();
                                            Toast.makeText(view.getContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        mProgressDialog.dismiss();
                                        Toast.makeText(view.getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mProgressDialog.dismiss();
                            Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    queue.add(request);
                }
            }
        });

        binding.forgotpassTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ForgotPassActivity.class);
                i.putExtra("data","user");
                startActivity(i);
            }
        });

        binding.goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        return binding.getRoot();
    }
}