package com.gracielo.probis2.Pembeli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.databinding.ItemBarangLayoutBinding;
import com.gracielo.probis2.databinding.ItemBarangPenjualTocartBinding;

import java.util.ArrayList;

public class ListBarangPembeliAdapter extends RecyclerView.Adapter<ListBarangPembeliAdapter.ViewHolder> {

    ArrayList<Barang>listBarang;
    int jumlah;
    public ListBarangPembeliAdapter(ArrayList<Barang>listBarang) {
        this.listBarang=listBarang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBarangPenjualTocartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Barang barang =listBarang.get(position);
        Glide.with(holder.binding.getRoot())
                .load(barang.getLinkGambar()).apply(new RequestOptions().override(100,100)).into(holder.binding.itemGambar);
        holder.binding.txtNamaBarang.setText(barang.getNama());
        holder.binding.txtHargaBarang.setText(String.format("Rp. %,d",Integer.parseInt(barang.getHarga())));
        holder.binding.txtSatuanBarang2.setText("- Per ".concat(barang.getSatuan()));
        jumlah = Integer.parseInt(holder.binding.txtJumlah.getText().toString());
        holder.binding.btnMinBarang.setOnClickListener(v -> {
            if(jumlah>0){
                jumlah--;
                holder.binding.txtJumlah.setText(String.valueOf(jumlah));
            }
        });
        holder.binding.btnPlusBarang.setOnClickListener(v -> {
            jumlah++;
            holder.binding.txtJumlah.setText(String.valueOf(jumlah));
        });

    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBarangPenjualTocartBinding binding;
        public ViewHolder(ItemBarangPenjualTocartBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
