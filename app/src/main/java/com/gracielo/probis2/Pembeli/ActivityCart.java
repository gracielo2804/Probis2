package com.gracielo.probis2.Pembeli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.ActivityCartBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityCart extends AppCompatActivity {

    ArrayList<Barang> listBarang = new ArrayList<>();
    ActivityCartBinding binding;
    ListBarangPembeliAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSemuaBarangPenjual(2);
        binding.btnToCheckout.setOnClickListener(v -> {
            Intent i =new Intent(this,ActivityCheckout.class);
            startActivity(i);
        });

    }

    void getSemuaBarangPenjual(int userid){
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,//tipe method pada web service
                getResources().getString(R.string.url),//url yang diakses
                //untuk handle respon
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e ( "response", "" + response );
                        System.out.println(response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int code=jsonObject.getInt("code");
                            System.out.println(response);
                            JSONArray databarang=jsonObject.getJSONArray("databarang");
                            if(code==1){
                                listBarang=new ArrayList();
                                for (int i=0;i<databarang.length();i++){
                                    JSONObject barangobj=databarang.getJSONObject(i);
                                    Barang barang=new Barang(
                                            barangobj.getInt("id"),
                                            barangobj.getString("nama"),
                                            String.valueOf(barangobj.getInt("harga")),
                                            barangobj.getString("deskripsi"),
                                            barangobj.getString("satuan"),
                                            barangobj.getString("linkgambar"),
                                            String.valueOf(barangobj.getInt("id_kategori"))
                                    );
                                    listBarang.add(barang);
                                }
                                binding.rvBarangCart.setHasFixedSize(true);
                                binding.rvBarangCart.setLayoutManager(new LinearLayoutManager(ActivityCart.this));
                                adapter=new ListBarangPembeliAdapter(listBarang);
                                binding.rvBarangCart.setAdapter(adapter);

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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String>params=new HashMap();
                params.put("function","getbaranguser");
                params.put("ID_Users",String.valueOf(2));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}