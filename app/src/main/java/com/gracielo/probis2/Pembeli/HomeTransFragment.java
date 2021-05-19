package com.gracielo.probis2.Pembeli;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Class.Users;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.FragmentHomeTransBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeTransFragment extends Fragment {

    FragmentHomeTransBinding binding;
    ArrayList<Users>listUser=new ArrayList<>();
    AdapterListPenjual adapter;

    public HomeTransFragment() {

    }



    public static HomeTransFragment newInstance() {
        HomeTransFragment fragment = new HomeTransFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeTransBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                Toast.makeText(getActivity(), "Ikan", Toast.LENGTH_SHORT).show();
            }
        });
        btnSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sayur", Toast.LENGTH_SHORT).show();
            }
        });
        btnBeras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Beras", Toast.LENGTH_SHORT).show();
            }
        });
        btnAyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ayam", Toast.LENGTH_SHORT).show();
            }
        });
        btnBuah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Buah", Toast.LENGTH_SHORT).show();
            }
        });
        btnDaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Daging", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvListPenjual.setLayoutManager(horizontalLayoutManagaer);
        binding.rvListPenjual.setHasFixedSize(false);
        getAllPenjual();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
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
                                        listUser.add(u);
                                    }
                                }
                                adapter=new AdapterListPenjual(listUser);
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
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}