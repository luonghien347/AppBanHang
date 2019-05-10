package com.th.thuhien.plantshop.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.RecyclerViewSPAdapter;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class SapXepSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarSapXepSpMoi;
    RecyclerView recyclerViewSapXepSpMoi;

    ArrayList<SanPham> data;
    RecyclerViewSPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sap_xep_san_pham);

        AnhXa();
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarSapXepSpMoi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSapXepSpMoi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarSapXepSpMoi = (Toolbar) findViewById(R.id.toolbarSapxepSPMoi);
        recyclerViewSapXepSpMoi = (RecyclerView) findViewById(R.id.recyclerviewSapXepSpMoi);

        data = new ArrayList<>();
        adapter = new RecyclerViewSPAdapter(this, R.layout.dong_sanpham_recyclerview, data);
        recyclerViewSapXepSpMoi.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewSapXepSpMoi.setLayoutManager(layoutManager);
        recyclerViewSapXepSpMoi.setAdapter(adapter);

        AsynListSapXepSpMoi asynListSapXepSpMoi = new AsynListSapXepSpMoi();
        asynListSapXepSpMoi.execute();
    }

    public class AsynListSapXepSpMoi extends AsyncTask<Void, Void, List<SanPham>> {


        @Override
        protected List<SanPham> doInBackground(Void... voids) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.sapXepSanPhamMoiNhat();
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            for (int i = 0; i< sanPhams.size(); i++){
                data.add(sanPhams.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }
}
