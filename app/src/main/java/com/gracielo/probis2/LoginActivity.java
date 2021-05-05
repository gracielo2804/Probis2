package com.gracielo.probis2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Transaksi.TransaksiActivity;
import com.gracielo.probis2.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=true;
                if (binding.etEmailLogin.getText().toString().trim().equalsIgnoreCase("")) {
                    binding.etEmailLogin.setError("Field ini Harus diisi");
                    binding.etEmailLogin.requestFocus();
                    status=false;
                }
                if (binding.etPassLogin.getText().toString().isEmpty()) {
                    binding.etPassLogin.setError("Field ini Harus diisi");
                    binding.etPassLogin.requestFocus();
                    status=false;
                }
                if(status)LoginProcess();
            }
        });
        binding.btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }


    public void LoginProcess(){
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,//tipe method pada web service
                getResources().getString(R.string.url),//url yang diakses
                //untuk handle respon
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int code=jsonObject.getInt("code");
                            String message=jsonObject.getString("message");
                            System.out.println(message);
                            if(code==1){
                                Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, TransaksiActivity.class);
                                startActivity(i);

                            }
                            else{
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                //untuk handle error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            //untuk mengatur parameter yang idkirim ke web sevice

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String>params=new HashMap();
                params.put("function","login");
                params.put("email",binding.etEmailLogin.getText().toString());
                params.put("password",binding.etPassLogin.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}