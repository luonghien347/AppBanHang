package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ManHinhSanPhamAdapter extends BaseAdapter {

    private ArrayList<SanPham> arrayMHSanPHam;
    private LayoutInflater layoutInflater;
    private Context context;


    public ManHinhSanPhamAdapter(Context context, ArrayList<SanPham> arrayMHSanPHam) {
        this.context = context;
        this.arrayMHSanPHam = arrayMHSanPHam;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayMHSanPHam.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayMHSanPHam.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MHViewHolder viewHolder;

        if (convertView == null){

            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_manhinh_sanpham, null);

            viewHolder = new MHViewHolder();

            viewHolder.txtMHTenSP = (TextView) convertView.findViewById(R.id.textviewMHTenSP);
            viewHolder.txtMHGiaSP = (TextView) convertView.findViewById(R.id.textviewMHGiaSP);
            viewHolder.txtMHMotaSP = (TextView) convertView.findViewById(R.id.textviewMHMotaSP);
            viewHolder.imgMHSP = (ImageView) convertView.findViewById(R.id.imageviewMHSanPham);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MHViewHolder) convertView.getTag();
        }

        SanPham sanPham = arrayMHSanPHam.get(position);
        viewHolder.txtMHTenSP.setText(sanPham.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtMHGiaSP.setText("Giá: " + decimalFormat.format(sanPham.getGiaSp()) + "Đ");
        viewHolder.txtMHMotaSP.setMaxLines(2);
        viewHolder.txtMHMotaSP.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMHMotaSP.setText(sanPham.getThongTin());
        Picasso.with(context).load(sanPham.getHinhAnh())
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(viewHolder.imgMHSP);
        return convertView;
    }

    static class MHViewHolder{
        TextView txtMHTenSP, txtMHGiaSP, txtMHMotaSP;
        ImageView imgMHSP;
    }
}
