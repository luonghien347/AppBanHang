package com.th.thuhien.plantshop.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.GiohangAdapter;
import com.th.thuhien.plantshop.model.GioHang;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    Button btnPrev, btnNext;
    ListView lv;
    static TextView total;
    TextView txtThongBaoGH;
    android.support.v7.widget.Toolbar toolbarGioHang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        setControl();
        toolbarAction();
        checkData();
        EvenUltil();
//        CatchOnItemLV();
        setEvent();
    }

    private void setControl() {
        lv = (ListView) findViewById(R.id.listviewGioHang);
        txtThongBaoGH = (TextView) findViewById(R.id.txtThongBaoGH);
        total = (TextView) findViewById(R.id.total);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        toolbarGioHang = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarGioHang);
        btnNext = (Button) findViewById(R.id.btnNext);
        giohangAdapter = new GiohangAdapter(GioHangActivity.this, MainActivity.arrayGioHang);
        lv.setAdapter(giohangAdapter);
    }

    private void setEvent(){

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.arrayGioHang.size() <=0){
                            txtThongBaoGH.setVisibility(View.VISIBLE);
                        }
                        else {
                            MainActivity.arrayGioHang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.arrayGioHang.size() <= 0){
                                txtThongBaoGH.setVisibility(View.VISIBLE);
                            }
                            else {
                                txtThongBaoGH.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayGioHang.size() >0 ){
                    Intent intent = new Intent(getApplicationContext(),CheckOut.class);
                    startActivity(intent);
                }
                else {
//                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn trống!");
                    Toast.makeText(GioHangActivity.this, "Giỏ hàng của bạn đang trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void CatchOnItemLV() {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.arrayGioHang.size() <=0){
                            txtThongBaoGH.setVisibility(View.VISIBLE);
                        }
                        else {
                            MainActivity.arrayGioHang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.arrayGioHang.size() <= 0){
                                txtThongBaoGH.setVisibility(View.VISIBLE);
                            }
                            else {
                                txtThongBaoGH.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void toolbarAction(){
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkData(){
        if (MainActivity.arrayGioHang.size() <= 0){
            giohangAdapter.notifyDataSetChanged();
            txtThongBaoGH.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
        }
        else{
            giohangAdapter.notifyDataSetChanged();
            txtThongBaoGH.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.VISIBLE);
        }
    }

    public static void EvenUltil(){
        long tongtien = 0;
        for (int i = 0; i<MainActivity.arrayGioHang.size(); i++){
            tongtien += MainActivity.arrayGioHang.get(i).getGiaSP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        total.setText(decimalFormat.format(tongtien) + " vnd");
    }
}
