package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    ArrayList<Menu> arrayListMenu;
    Context context;

    public MenuAdapter(ArrayList<Menu> arrayListMenu, Context context) {
        this.arrayListMenu = arrayListMenu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView textViewTenMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_menu,null);
            viewHolder.textViewTenMenu = convertView.findViewById(R.id.textViewListMenu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Menu menu = (Menu) getItem(position);
        viewHolder.textViewTenMenu.setText(menu.getTenMenu());
        return convertView;
    }
}
