package com.gracielo.probis2.Penjual;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.databinding.ItemBarangLayoutBinding;

import java.util.ArrayList;

public class ListBarangAdapter extends RecyclerView.Adapter<ListBarangAdapter.ViewHolder> {

    ArrayList<Barang>listBarang;
    public ListBarangAdapter(ArrayList<Barang>listBarang) {
        this.listBarang=listBarang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBarangLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Barang barang =listBarang.get(position);
        Glide.with(holder.binding.getRoot())
                .load(barang.getLinkGambar()).apply(new RequestOptions().override(100,100)).into(holder.binding.itemGambar);
        holder.binding.txtNamaBarang.setText(barang.getNama());
        holder.binding.txtHargaBarang.setText(String.format("Rp. %,d",Integer.parseInt(barang.getHarga())));
        holder.binding.txtSatuanBarang.setText("- Per ".concat(barang.getSatuan()));

    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBarangLayoutBinding binding;
        public ViewHolder(ItemBarangLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
