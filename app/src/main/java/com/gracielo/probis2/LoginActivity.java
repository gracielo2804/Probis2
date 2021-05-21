package com.gracielo.probis2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Pembeli.HomeTransaksiActivity;
import com.gracielo.probis2.Penjual.HomePenjualActivity;
import com.gracielo.probis2.databinding.ActivityLoginBinding;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    int UserIDLog=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etEmailLog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    binding.txtFieldEmailLog.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etPassLog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    binding.txtFieldPassLog.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=true;
                if (binding.etEmailLog.getText().toString().trim().equalsIgnoreCase("")) {
                    binding.txtFieldEmailLog.setErrorEnabled(true);
                    binding.txtFieldEmailLog.setError("Field ini Harus diisi");
                    status=false;
                }
                if (binding.etPassLog.getText().toString().isEmpty()) {
                    binding.txtFieldPassLog.setErrorEnabled(true);
                    binding.txtFieldPassLog.setError("Field ini Harus diisi");
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
                            JSONObject userObj = jsonObject.getJSONObject("datauser");
                            int id= userObj.getInt("id");
                            System.out.println(id);
                            if(code==11){
                                Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, HomeTransaksiActivity.class);
                                i.putExtra("ID",id);
                                startActivity(i);

                            }
                            if(code==12){
                                Toast.makeText(LoginActivity.this, "Berhasil Login Penjual", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, HomePenjualActivity.class);
                                i.putExtra("ID",id);
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
                params.put("email",binding.etEmailLog.getText().toString());
                params.put("password",binding.etPassLog.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}