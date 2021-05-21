package com.gracielo.probis2.Pembeli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gracielo.probis2.Class.Users;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.ActivityHomeTransaksiBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeTransaksiActivity extends AppCompatActivity {

    Fragment fragment;
    ActivityHomeTransaksiBinding binding;

    ArrayList<Users> listUser=new ArrayList<>();
    AdapterListPenjual adapter;
    int iduserlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomeTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().hasExtra("ID"))iduserlog=getIntent().getIntExtra("ID",-1);
        getSupportActionBar().hide();

        binding.searchBarangHome.onActionViewExpanded();

        ImageButton btnIkan=binding.btnKategoriIkan;
        ImageButton btnSayur=binding.btnKategoriSayur;
        ImageButton btnBeras=binding.btnKategoriBeras;
        ImageButton btnAyam=binding.btnKategoriAyam;
        ImageButton btnBuah=binding.btnKategoriBuah;
        ImageButton btnDaging=binding.btnKategoriDaging;

        btnIkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Ikan", Toast.LENGTH_SHORT).show();
            }
        });
        btnSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Sayur", Toast.LENGTH_SHORT).show();
            }
        });
        btnBeras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Beras", Toast.LENGTH_SHORT).show();
            }
        });
        btnAyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Ayam", Toast.LENGTH_SHORT).show();
            }
        });
        btnBuah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Buah", Toast.LENGTH_SHORT).show();
            }
        });
        btnDaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeTransaksiActivity.this, "Daging", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(HomeTransaksiActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvListPenjual.setLayoutManager(horizontalLayoutManagaer);
        binding.rvListPenjual.setHasFixedSize(false);
//        adapter=new AdapterListPenjual(listUser);
//        adapter.setOnItemClickCallback(new AdapterListPenjual.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(Users user) {
//                System.out.println("asd");
//            }
//        });

        getAllPenjual();

//        notifyAdapter();


        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case(R.id.item_cart):
//                        fragment=new AddFragment();
//                        fragment=AddFragment.newInstance();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                        return true;
                    case (R.id.item_profile):
//                        fragment=ListFragment.newInstance();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                        return true;
                }
                return false;
            }
        });

//        if(savedInstanceState==null){
//            fragment=HomeTransFragment.newInstance();
//            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).commit();
//        }
    }

    void getAllPenjual(){
        listUser=new ArrayList<Users>();
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
                            JSONArray datamhs=jsonObject.getJSONArray("datauser");
                            if(code==1){
                                for (int i=0;i<datamhs.length();i++){
                                    JSONObject userobj=datamhs.getJSONObject(i);
                                    if (userobj.getInt("role")==2){
                                        Users u=new Users(
                                                userobj.getString("nama"),
                                                userobj.getString("alamat"),
                                                userobj.getString("jk"),
                                                userobj.getString("nomor"),
                                                userobj.getString("email"),
                                                userobj.getString("password"),
                                                userobj.getInt("role")
                                        );
                                        u.setId(userobj.getInt("id"));
                                        listUser.add(u);
                                    }
                                }
//                                adapter.notifyDataSetChanged();
                                adapter=new AdapterListPenjual(listUser);
                                adapter.setOnItemClickCallback(new AdapterListPenjual.OnItemClickCallback() {
                                    @Override
                                    public void onItemClicked(Users user) {
                                       Intent intent = new Intent(HomeTransaksiActivity.this,ActivityListBarangPenjual.class);
                                       intent.putExtra("iduserlog",iduserlog);
                                       intent.putExtra("penjual",user);
                                       startActivity(intent);
                                    }
                                });
                                binding.rvListPenjual.setAdapter(adapter);
                            }
//                            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
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
                params.put("function","getalluser");
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(HomeTransaksiActivity.this);
        requestQueue.add(stringRequest);
    }

//    void notifyAdapter(){
//        adapter.notifyDataSetChanged();
//        binding.rvListPenjual.setAdapter(adapter);
//
//    }
}