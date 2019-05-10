package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecyclerViewSPAdapter extends RecyclerView.Adapter<RecyclerViewSPAdapter.SpMoiViewHolder>{
    Context context;
    int layoutResourceId;
    ArrayList<SanPham> arraySanPham;

    public RecyclerViewSPAdapter(Context context, int layoutResourceId, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.arraySanPham = arraySanPham;
    }

    @NonNull
    @Override
    public SpMoiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResourceId, null);
        SpMoiViewHolder spMoiViewHolder = new SpMoiViewHolder(v);
        return spMoiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpMoiViewHolder spMoiViewHolder, int i) {
        SanPham sanPham = arraySanPham.get(i);
        spMoiViewHolder.tenSp.setText(sanPham.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        spMoiViewHolder.giaSp.setText("Giá: " + decimalFormat.format(sanPham.getGiaSp()) + "Đ");
        Picasso.with(context).load(sanPham.getHinhAnh())
                .error(R.drawable.error)
                .placeholder(R.drawable.product)
                .into(spMoiViewHolder.img_hinh);
    }

    @Override
    public int getItemCount() {
        return arraySanPham.size();
    }

    public class SpMoiViewHolder extends RecyclerView.ViewHolder{
        ImageView img_hinh;
        TextView tenSp, giaSp;

        public SpMoiViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hinh = (ImageView) itemView.findViewById(R.id.imageviewSapXepSpMoi);
            tenSp = (TextView) itemView.findViewById(R.id.textviewSapXepTenSpMoi);
            giaSp = (TextView) itemView.findViewById(R.id.textviewSapXepGiaSpMoi);
        }
    }
}
