package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.GioHang;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView txtTenCT, txtGiaCT, txtMoTaCT;
    Spinner spinnerCT;
    Button btnDatMuaCT;
    //RecyclerView recyclerViewSPCungLoai;

    int id = 0;
    String tenChiTiet = "";
    int giaChiTiet = 0;
    String hinhChiTiet = "";
    String motaChiTiet = "";
    int idSp = 0;
    int idMenu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        AnhXa();
        ActionToolbar();
        GetInfomation();
        CatchEventSpiner();
        EventButton();
        //EventRecyclerViewSPCungLoai();
//        EventButtonGioHang(); // dung de show man hinh gio hang thôi
    }

    private void EventRecyclerViewSPCungLoai() {
        
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btnDatMuaCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayGioHang.size() > 0){
                    int sl = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.arrayGioHang.size(); i++){
                        if (MainActivity.arrayGioHang.get(i).getIdSP() == id){
                            MainActivity.arrayGioHang.get(i).setSoluongSP(MainActivity.arrayGioHang.get(i).getSoluongSP() + sl);
                            if (MainActivity.arrayGioHang.get(i).getSoluongSP() >= 10){
                                MainActivity.arrayGioHang.get(i).setSoluongSP(10);
                            }
                            MainActivity.arrayGioHang.get(i).setGiaSP(giaChiTiet * MainActivity.arrayGioHang.get(i).getSoluongSP());
                            exists = true;
                        }
                    }
                    if (exists == false){
                        int soluong = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                        long giaMoi = soluong * giaChiTiet;
                        MainActivity.arrayGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                    long giaMoi = soluong * giaChiTiet;
                    MainActivity.arrayGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpiner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinnerCT.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanPham.getMaSp();
        tenChiTiet = sanPham.getTenSp();
        giaChiTiet = sanPham.getGiaSp();
        hinhChiTiet = sanPham.getHinhAnh();
        motaChiTiet = sanPham.getThongTin();
        idMenu = sanPham.getMaMenu();

        txtTenCT.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaCT.setText("Giá: " + decimalFormat.format(giaChiTiet) + "Đ");
        txtMoTaCT.setText(motaChiTiet);
        Picasso.with(getApplicationContext()).load(hinhChiTiet)
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(imgChiTiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChiTiet = (Toolbar) findViewById(R.id.toolbarChiTietSP);
        imgChiTiet = (ImageView) findViewById(R.id.imageviewChiTietSP);
        txtTenCT = (TextView) findViewById(R.id.textviewTenChiTietSP);
        txtGiaCT = (TextView) findViewById(R.id.textviewGiaChiTietSP);
        txtMoTaCT = (TextView) findViewById(R.id.textviewMoTaCTSP);
        spinnerCT = (Spinner) findViewById(R.id.spinerSoLuongCTSP);
        btnDatMuaCT = (Button) findViewById(R.id.buttonDatMuaCT);
        //recyclerViewSPCungLoai = (RecyclerView) findViewById(R.id.recyclerviewSPCungLoai);
    }

}