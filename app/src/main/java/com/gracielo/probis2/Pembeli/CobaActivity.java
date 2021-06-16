package com.gracielo.probis2.Pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.LoginActivity;
import com.gracielo.probis2.Penjual.HomePenjualActivity;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.ActivityCobaBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CobaActivity extends AppCompatActivity {

    ActivityCobaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCobaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void AddtoCart(int idbarang,int iduser){
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
                            JSONObject userObj = jsonObject.getJSONObject("datauser");
                            int id= userObj.getInt("id");
                            if(code==1){
                                Toast.makeText(CobaActivity.this, "Berhasil Tambah Barang ke cart", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(CobaActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String>params=new HashMap();
                params.put("function","addCart");
                params.put("idbarang",String.valueOf(idbarang));
                params.put("idUser",String.valueOf(iduser));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}