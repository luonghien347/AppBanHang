package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.activity.ChiTietSanPhamActivity;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {

    Context context;
    int layoutResourceId;
    ArrayList<SanPham> arraySanPham;

    public SanPhamAdapter(Context context, int layoutResourceId, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.arraySanPham = arraySanPham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResourceId, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        SanPham sanPham = arraySanPham.get(i);
        itemHolder.txtTenSanPham.setText(sanPham.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder.txtGiaSanPham.setText("Giá: " +  decimalFormat.format(sanPham.getGiaSp()) + "Đ");
        Picasso.with(context).load(sanPham.getHinhAnh())
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(itemHolder.imageSanPham);
    }

    @Override
    public int getItemCount() {
        return arraySanPham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageSanPham;
        public TextView txtTenSanPham, txtGiaSanPham;

        public ItemHolder(View itemView) {
            super(itemView);
            imageSanPham = itemView.findViewById(R.id.imageviewSanPham);
            txtTenSanPham = itemView.findViewById(R.id.textviewTenSanPham);
            txtGiaSanPham = itemView.findViewById(R.id.textviewGiaSanPham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham", arraySanPham.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
