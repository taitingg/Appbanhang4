package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.InterfaceUsing.ItemDeleteClickListener;
import com.example.appbanhang.Model.DonHang;
import com.example.appbanhang.R;
import com.example.appbanhang.Utils.Utils;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    Context context;
    List<DonHang> list;
    ItemDeleteClickListener itemDeleteClickListener;

    public DonHangAdapter(Context context, List<DonHang> list, ItemDeleteClickListener itemDeleteClickListener) {
        this.context = context;
        this.list = list;
        this.itemDeleteClickListener = itemDeleteClickListener;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_don_hang,parent,false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        DonHang donHang = list.get(position);
        holder.tv_donHang.setText("Đơn hàng: " + donHang.getId()+"");
        holder.tv_trangThai.setText(Utils.statusOrder(donHang.getTrangthai()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemDeleteClickListener.onClickDelete(donHang.getId());
                return false;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
        holder.recyclerView_chitiet.getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(donHang.getItem(),context);
        holder.recyclerView_chitiet.setLayoutManager(linearLayoutManager);
        holder.recyclerView_chitiet.setAdapter(chiTietAdapter);
        holder.recyclerView_chitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {

        TextView tv_donHang,tv_trangThai;
        RecyclerView recyclerView_chitiet;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_donHang = itemView.findViewById(R.id.id_donHang);
            recyclerView_chitiet = itemView.findViewById(R.id.recycleView_chitiet);
            tv_trangThai = itemView.findViewById(R.id.tv_trangthai);
        }
    }
}
