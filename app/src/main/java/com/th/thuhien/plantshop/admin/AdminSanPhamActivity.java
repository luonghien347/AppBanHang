package com.th.thuhien.plantshop.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.ManHinhSanPhamAdapter;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.MenuService;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarAdminSanPham;
    TextView tv_AdminTenMenu;
    ListView lv_AdminSanPham;
    Spinner spinnerListMenu;
    CircleImageView img_themSp;

    ArrayList<SanPham> data;
    ManHinhSanPhamAdapter adapter;

    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_san_pham);

        AnhXa();
        ActionBar();
        setEventSelectItemSpinner();
        getListMenu();
        //setEventThemSp();

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_sanpham, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_them_sanpham:
                Intent intent = new Intent(AdminSanPhamActivity.this, AdminThemSanPhamActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setEventThemSp() {
//        img_themSp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminSanPhamActivity.this, AdminThemSanPhamActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    private void getListMenu() {
        listMenu.add(new Menu("All"));
        AsynListAdminMenu asynListAdminMenu = new AsynListAdminMenu();
        asynListAdminMenu.execute();
    }

    private void setEventSelectItemSpinner() {
        spinnerListMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("pos", data.get(position).getTenSp());
                if (position == 0){
                    tv_AdminTenMenu.setText("Danh sách tất cả các cây");
                    data.clear();
                    getListSP();
                }else {
                    tv_AdminTenMenu.setText("Danh sách các " + listMenu.get(position).getTenMenu());
                    data.clear();
                    getListSanPhamTheoMenu(listMenu.get(position).getMaMenu());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void getListSP() {
        AsynAdminListSanPham asynAdminListSanPham = new AsynAdminListSanPham();
        asynAdminListSanPham.execute();
    }

    private void getListSanPhamTheoMenu(int pos) {
        AsynAdminSanPhamTheoMenu asynAdminSanPhamTheoMenu = new AsynAdminSanPhamTheoMenu();
        asynAdminSanPhamTheoMenu.execute(pos);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarAdminSanPham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdminSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarAdminSanPham = (Toolbar) findViewById(R.id.toolbarAdminSanPham);
        tv_AdminTenMenu = (TextView) findViewById(R.id.textviewAdminTenMenu);
        lv_AdminSanPham = (ListView) findViewById(R.id.listviewAdminSanPham);
        spinnerListMenu = (Spinner) findViewById(R.id.spinnerListMenu);
        //img_themSp = (CircleImageView) findViewById(R.id.imageviewThemSp);

        data = new ArrayList<>();
        adapter = new ManHinhSanPhamAdapter(getApplicationContext(), data);
        lv_AdminSanPham.setAdapter(adapter);

        listMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(listMenu, getApplicationContext());
        spinnerListMenu.setAdapter(menuAdapter);

    }

    public class AsynAdminListSanPham extends AsyncTask<Void, Void, List<SanPham>>{

        @Override
        protected List<SanPham> doInBackground(Void... voids) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getListSanPham();
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            for (int i = 0; i < sanPhams.size(); i++){
                data.add(sanPhams.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }

    public class AsynListAdminMenu extends AsyncTask<Void, Void, List<Menu>>{


        @Override
        protected List<Menu> doInBackground(Void... voids) {
            MenuService menuService = new MenuService();
            return menuService.getListMenu();
        }

        @Override
        protected void onPostExecute(List<Menu> menu) {
            super.onPostExecute(menu);
            for (int i = 0; i < menu.size(); i++){
                listMenu.add(menu.get(i));
            }
            menuAdapter.notifyDataSetChanged();
        }
    }

    public class AsynAdminSanPhamTheoMenu extends AsyncTask<Integer, Void, List<SanPham>>{


        @Override
        protected List<SanPham> doInBackground(Integer... integers) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getSanPhamByMenu(integers[0]);
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            for (int i = 0; i < sanPhams.size(); i++){
                data.add(sanPhams.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }


}
