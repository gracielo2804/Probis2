package com.gracielo.probis2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Class.Users;
import com.gracielo.probis2.databinding.ActivityRegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    ArrayList<Users> listUser=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getAllUser();
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=true;
                if(binding.etConPassReg.getText().toString().isEmpty()){
                    binding.etConPassReg.setError("Field Ini Harus Diisi !");
                    binding.etConPassReg.requestFocus();
                    status=false;
                }
                if(binding.etPassReg.getText().toString().isEmpty()){
                    binding.etPassReg.setError("Field Ini Harus Diisi !");
                    binding.etPassReg.requestFocus();
                    status=false;
                }
                if(binding.etEmailReg.getText().toString().isEmpty()){
                    binding.etEmailReg.setError("Field Ini Harus Diisi !");
                    binding.etEmailReg.requestFocus();
                    status=false;
                }
                if(binding.etNomorReg.getText().toString().isEmpty()){
                    binding.etNomorReg.setError("Field Ini Harus Diisi !");
                    binding.etNomorReg.requestFocus();
                    status=false;
                }
                if(binding.etAlamatReg.getText().toString().isEmpty()){
                    binding.etAlamatReg.setError("Field Ini Harus Diisi !");
                    binding.etAlamatReg.requestFocus();
                    status=false;
                }
                if(binding.etNamaReg.getText().toString().isEmpty()){
                    binding.etNamaReg.setError("Field Ini Harus Diisi !");
                    binding.etNamaReg.requestFocus();
                    status=false;
                }
                if(!binding.etConPassReg.getText().toString().equals(binding.etPassReg.getText().toString())){
                    binding.etConPassReg.setError("Password Dan Konfirmasi Password Harus Sama !");
                    binding.etConPassReg.requestFocus();
                    status=false;
                }
                if(status){
                    boolean cekkembar=true;
                    for(int i=0;i<listUser.size();i++){
                        if(binding.etEmailReg.getText().toString().trim().equals(listUser.get(i).getEmail())){
                            binding.etEmailReg.setError("Email Sudah Digunakan !");
                            binding.etEmailReg.requestFocus();
                            cekkembar=false;
                        }
                    }
                    if(cekkembar){
                        String jk="";
                        int selectedrb=binding.rgJK.getCheckedRadioButtonId();
                        RadioButton rbselected=findViewById(selectedrb);
                        jk=rbselected.getText().toString();
                        int selectedrbRole=binding.rgRole.getCheckedRadioButtonId();
                        RadioButton rbselectedrole=findViewById(selectedrb);
                        int role = 1;
                        role = (rbselectedrole.getText().toString().equals("Pembeli")) ? 1 : 2;
                        System.out.println(role);
//                        if(rbselectedrole.getText().toString().equals("Pembeli"))
//                            role=1;

                        Users u =new Users(binding.etNamaReg.getText().toString(),
                                binding.etAlamatReg.getText().toString(),
                                jk,
                                binding.etNomorReg.getText().toString(),
                                binding.etEmailReg.getText().toString(),
                                binding.etPassReg.getText().toString(),
                                role);
                        AddUserProcess(u);
                    }
                }
            }
        });
    }
    RegisterActivity activity=this;

    private void AddUserProcess(final Users user){
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
                            String message=jsonObject.getString("message");
                            if(code==1){
                                Toast.makeText(activity, "Berhasil Register !", Toast.LENGTH_SHORT).show();
                                finish();
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
                params.put("function","addUser");
                params.put("nama",String.valueOf(user.getNama()));
                params.put("JK", user.getJk());
                params.put("alamat",user.getAlamat());
                params.put("nomor",user.getNomor());
                params.put("email",user.getEmail());
                params.put("password",user.getPassword());
                params.put("role",user.getRoleString());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }

    void getAllUser(){
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
        RequestQueue requestQueue= Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }




}