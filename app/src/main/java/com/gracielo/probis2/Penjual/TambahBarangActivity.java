package com.gracielo.probis2.Penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.ActivityTambahBarangBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahBarangActivity extends AppCompatActivity {

    ActivityTambahBarangBinding binding;
    int idUserLog=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTambahBarangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent().hasExtra("ID"))idUserLog=getIntent().getIntExtra("ID",-1);
        binding.btnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=true;
                boolean stateGambar=true;
                boolean stateDesc=true;
                if(binding.etNamaBarangTambah.getText().toString().isEmpty()){
                    status=false;
                    binding.txtFieldNamaBarang.setError("Field Ini Harus Diisi !");
                }
                if(binding.etHargaBarangTambah.getText().toString().isEmpty()){
                    status=false;
                    binding.txtFieldHargaBarang.setError("Field Ini Harus Diisi !");
                }
                if(binding.etSatuanBarangTambah.getText().toString().isEmpty()){
                    status=false;
                    binding.txtFieldSatuanBarang.setError("Field Ini Harus Diisi");
                }

                if(binding.spKategori.getSelectedItemPosition()==0){
                    status=false;
                    ((TextView)binding.spKategori.getSelectedView()).setError("Silahkan Pilih Kategori");
                }
                else{
                }

                if(binding.etGambarBarangTambah.getText().toString().isEmpty()){
                    stateGambar=false;
                }
                if(binding.etDeskripsiBarangTambah.getText().toString().isEmpty()){
                    stateDesc=false;
                }

                if(status){
                    String desc,gambar;
                    desc = (stateDesc) ? binding.etDeskripsiBarangTambah.getText().toString() : "";
                    gambar = (stateGambar) ? binding.etGambarBarangTambah.getText().toString() : "";
                    Barang barang=new Barang(0,
                            binding.etNamaBarangTambah.getText().toString(),
                            String.valueOf(binding.etHargaBarangTambah.getText()),
                            desc,
                            binding.etSatuanBarangTambah.getText().toString(),
                            gambar,
                            String.valueOf(binding.spKategori.getSelectedItemPosition())
                            );

                    insertBarang(barang,idUserLog);

                }
            }
        });


    }
    void insertBarang(Barang barang,int UserID){
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
                                Toast.makeText(TambahBarangActivity.this, "Berhasil Tambah Barang !", Toast.LENGTH_SHORT).show();
                                setResult(HomePenjualActivity.RESULT_CODE);
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
                params.put("function","addBarang");
                params.put("nama",barang.getNama());
                params.put("harga", barang.getHarga());
                params.put("deskripsi",barang.getDesc());
                params.put("satuan",barang.getSatuan());
                params.put("linkgambar",barang.getLinkGambar());
                params.put("id_penjual",String.valueOf(UserID));
                params.put("id_kategori",barang.getIdKategori());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}