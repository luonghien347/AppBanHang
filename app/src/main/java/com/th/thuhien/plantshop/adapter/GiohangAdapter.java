package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.activity.GioHangActivity;
import com.th.thuhien.plantshop.activity.MainActivity;
import com.th.thuhien.plantshop.model.GioHang;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang> dataGH;

    public GiohangAdapter(Context context, ArrayList<GioHang> dataGH) {
        this.context = context;
        this.dataGH = dataGH;
    }

    @Override
    public int getCount() {
        return dataGH.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataGH.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class GHHolder {
        public TextView txtName, txtGia;
        public ImageView icon;
        public Button add, del,quantity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GHHolder holder = null;
        if (holder == null){
            holder = new GHHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.item_row_giohang, null);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtGia = (TextView) convertView.findViewById(R.id.txtGia);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.quantity = (Button) convertView.findViewById(R.id.quantity);
            holder.add = (Button) convertView.findViewById(R.id.btncong);
            holder.del = (Button) convertView.findViewById(R.id.btntru);
            convertView.setTag(holder);
        }
        else {
            holder = (GHHolder) convertView.getTag();
        }
        final GioHang gioHang = (GioHang) getItem(position);
        holder.txtName.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gioHang.getGiaSP())+" vnd");
        Picasso.with(context).load(gioHang.getHinhSP())
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(holder.icon);
        holder.quantity.setText(gioHang.soluongSP + "");
        final int sl = Integer.parseInt(holder.quantity.getText().toString());
        if( sl >=10){
            holder.add.setVisibility(View.INVISIBLE);
            holder.del.setVisibility(View.VISIBLE);
        }
        else if(sl<=1){
            holder.del.setVisibility(View.INVISIBLE);
        }
        else if(sl>=1){
            holder.add.setVisibility(View.VISIBLE);
            holder.del.setVisibility(View.VISIBLE);
        }
        final GHHolder finalHolder = holder;
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slnew = Integer.parseInt(finalHolder.quantity.getText().toString())+1;
                int slht = MainActivity.arrayGioHang.get(position).getSoluongSP();
                long giaht = MainActivity.arrayGioHang.get(position).getGiaSP();
                MainActivity.arrayGioHang.get(position).setSoluongSP(slnew);
                long gianew = (giaht * slnew) / slht;
                MainActivity.arrayGioHang.get(position).setGiaSP(gianew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalHolder.txtGia.setText(decimalFormat.format(gianew)+" vnd");
                GioHangActivity.EvenUltil();
                if (slnew > 9 ){
                    finalHolder.add.setVisibility(View.INVISIBLE);
                    finalHolder.del.setVisibility(View.VISIBLE);
                    finalHolder.quantity.setText(String.valueOf(slnew));
                }
                else{
                    finalHolder.add.setVisibility(View.VISIBLE);
                    finalHolder.del.setVisibility(View.VISIBLE);
                    finalHolder.quantity.setText(String.valueOf(slnew));
                }
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slnew = Integer.parseInt(finalHolder.quantity.getText().toString())-1;
                int slht = MainActivity.arrayGioHang.get(position).getSoluongSP();
                long giaht = MainActivity.arrayGioHang.get(position).getGiaSP();
                MainActivity.arrayGioHang.get(position).setSoluongSP(slnew);
                long gianew = (giaht * slnew) / slht;
                MainActivity.arrayGioHang.get(position).setGiaSP(gianew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalHolder.txtGia.setText(decimalFormat.format(gianew)+" vnd");
                GioHangActivity.EvenUltil();
                if (slnew < 2 ){
                    finalHolder.del.setVisibility(View.INVISIBLE);
                    finalHolder.add.setVisibility(View.VISIBLE);
                    finalHolder.quantity.setText(String.valueOf(slnew));
                }else{
                    finalHolder.add.setVisibility(View.VISIBLE);
                    finalHolder.del.setVisibility(View.VISIBLE);
                    finalHolder.quantity.setText(String.valueOf(slnew));
                }
            }
        });

        return convertView;
    }
}
