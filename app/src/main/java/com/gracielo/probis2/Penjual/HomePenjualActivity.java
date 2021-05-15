package com.gracielo.probis2.Penjual;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.R;
import com.gracielo.probis2.Class.Users;
import com.gracielo.probis2.databinding.ActivityHomePenjualBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomePenjualActivity extends AppCompatActivity {

    ActivityHomePenjualBinding binding;
    Users userslog;
    int idlog;
    ArrayList<Barang> listBarang = new ArrayList<>();

    static final int REQUEST_CODE = 111;
    static final int RESULT_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomePenjualBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent().hasExtra("ID"))idlog=getIntent().getIntExtra("ID",-1);
        System.out.println(idlog);
        if(idlog!=-1){
            getUserByID(idlog);
        }
        binding.btnAddBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePenjualActivity.this,TambahBarangActivity.class);
                i.putExtra("ID",idlog);
                startActivityForResult(i,REQUEST_CODE);
            }
        });
    }

    void getUserByID(int id){
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
                        if(code==1){
                            JSONObject userobj=jsonObject.getJSONObject("datauser");
                            Users u=new Users(
                                    userobj.getString("nama"),
                                    userobj.getString("alamat"),
                                    userobj.getString("jk"),
                                    userobj.getString("nomor"),
                                    userobj.getString("email"),
                                    userobj.getString("password"),
                                    userobj.getInt("role")
                            );
                            userslog=u;
                            binding.txtNamaPenjual.setText(userslog.getNama());
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
                params.put("function","getUserByID");
                params.put("ID_Users",String.valueOf(idlog));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_CODE){
            getUserByID(idlog);
        }
    }

    void getSemuaBarangUser(int userid){
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
                params.put("function","getUserByID");
                params.put("ID_Users",String.valueOf(idlog));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}