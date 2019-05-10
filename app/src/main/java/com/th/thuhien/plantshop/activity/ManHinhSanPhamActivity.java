package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.ManHinhSanPhamAdapter;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class ManHinhSanPhamActivity extends AppCompatActivity {

    Toolbar toolbar_MHSP;
    ArrayList<SanPham> arrayMHSP;
    ListView lv_MHSP;
    ManHinhSanPhamAdapter manHinhSanPhamAdapter;
    Integer maMenu = 0;
    String tenmenu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_san_pham);


        AnhXa();
        GetIdMenu();
        ActionToolbar();

        //setEvent();
        LoadMoreData();
    }

    private void LoadMoreData() {
        lv_MHSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", arrayMHSP.get(position));
                startActivity(intent);
            }
        });
    }

//    private void setEvent() {
//        arrayMHSP = KhoiTao();
//        lv_MHSP.setAdapter(new ManHinhSanPhamAdapter(this, arrayMHSP));
////        manHinhSanPhamAdapter = new ManHinhSanPhamAdapter(getApplicationContext(), arrayMHSP);
////        lv_MHSP.setAdapter(manHinhSanPhamAdapter);
//    }


    private void ActionToolbar() {
        setSupportActionBar(toolbar_MHSP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(tenmenu);
        toolbar_MHSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdMenu() {
        Intent intent = getIntent();
        maMenu = intent.getIntExtra("maMenu", -1);
        tenmenu = intent.getStringExtra("tenmenu");
        Log.d("giatrimamenu", maMenu + "");
        Log.d("giatritenmamenu", tenmenu + "");
    }

    private void AnhXa() {
        toolbar_MHSP = (Toolbar) findViewById(R.id.toolbarMHSanPham);
        lv_MHSP = (ListView) findViewById(R.id.listviewMHSanPham);

        arrayMHSP = new ArrayList<>();
        manHinhSanPhamAdapter = new ManHinhSanPhamAdapter(this, arrayMHSP);
        lv_MHSP.setAdapter(manHinhSanPhamAdapter);

//        //arrayMHSP = KhoiTao();


////
////
////
////
        AsynMHSP asysnMHSP = new AsynMHSP();
        asysnMHSP.execute();



    }

    private List<SanPham> KhoiTao(){
        List<SanPham> list = new ArrayList<SanPham>();

        SanPham a = new SanPham(1, "Cây Kim Ngân xoắn", "https://1.bp.blogspot.com/-fCutH05sQt0/XJCKcbpPQoI/AAAAAAAAFyE/pVBOukSE3w0znA3jp_qnxkP0zCg4xVU-ACLcBGAs/s1600/cay-kim-ngan-xoan.jpg", "Cây Kim Ngân xoắn có tên khoa học là Pachia aquatica, xuất xứ từ Mexico, Brazill Nam Mỹ và đầm lầy Trung Mỹ. Cây cao trên 6m, thân dẻo dai, bền chắc nên được dùng làm bột giấy in tiền tại Anh và Mỹ. Có lẽ chính điều này mà người ta gọi Kim ngân xoắn là cây tiền. Lá cây Kim ngân xoắn xoè rộng như bàn tay, xanh quanh năm – một màu xanh tươi mát. Cây nở hoa từ tháng 4 đến tháng 11. Hoa gồm những cánh lớn màu kem nhạt, nở về đêm và toả hương thoang thoảng Đài hoa màu nâu nhạt hình bầu dục với 5 cánh màu xanh vàng, dài 15cm.", 320000, 1);
        SanPham b = new SanPham(2, "Cây Kim Ngân xoắn", "https://1.bp.blogspot.com/-fCutH05sQt0/XJCKcbpPQoI/AAAAAAAAFyE/pVBOukSE3w0znA3jp_qnxkP0zCg4xVU-ACLcBGAs/s1600/cay-kim-ngan-xoan.jpg", "Cây Kim Ngân xoắn có tên khoa học là Pachia aquatica, xuất xứ từ Mexico, Brazill Nam Mỹ và đầm lầy Trung Mỹ. Cây cao trên 6m, thân dẻo dai, bền chắc nên được dùng làm bột giấy in tiền tại Anh và Mỹ. Có lẽ chính điều này mà người ta gọi Kim ngân xoắn là cây tiền. Lá cây Kim ngân xoắn xoè rộng như bàn tay, xanh quanh năm – một màu xanh tươi mát. Cây nở hoa từ tháng 4 đến tháng 11. Hoa gồm những cánh lớn màu kem nhạt, nở về đêm và toả hương thoang thoảng Đài hoa màu nâu nhạt hình bầu dục với 5 cánh màu xanh vàng, dài 15cm.", 320000, 1);
        SanPham c = new SanPham(3, "Cây Kim Ngân xoắn", "https://1.bp.blogspot.com/-fCutH05sQt0/XJCKcbpPQoI/AAAAAAAAFyE/pVBOukSE3w0znA3jp_qnxkP0zCg4xVU-ACLcBGAs/s1600/cay-kim-ngan-xoan.jpg", "Cây Kim Ngân xoắn có tên khoa học là Pachia aquatica, xuất xứ từ Mexico, Brazill Nam Mỹ và đầm lầy Trung Mỹ. Cây cao trên 6m, thân dẻo dai, bền chắc nên được dùng làm bột giấy in tiền tại Anh và Mỹ. Có lẽ chính điều này mà người ta gọi Kim ngân xoắn là cây tiền. Lá cây Kim ngân xoắn xoè rộng như bàn tay, xanh quanh năm – một màu xanh tươi mát. Cây nở hoa từ tháng 4 đến tháng 11. Hoa gồm những cánh lớn màu kem nhạt, nở về đêm và toả hương thoang thoảng Đài hoa màu nâu nhạt hình bầu dục với 5 cánh màu xanh vàng, dài 15cm.", 320000, 1);
        SanPham d = new SanPham(4, "Cây Kim Ngân xoắn", "https://1.bp.blogspot.com/-fCutH05sQt0/XJCKcbpPQoI/AAAAAAAAFyE/pVBOukSE3w0znA3jp_qnxkP0zCg4xVU-ACLcBGAs/s1600/cay-kim-ngan-xoan.jpg", "Cây Kim Ngân xoắn có tên khoa học là Pachia aquatica, xuất xứ từ Mexico, Brazill Nam Mỹ và đầm lầy Trung Mỹ. Cây cao trên 6m, thân dẻo dai, bền chắc nên được dùng làm bột giấy in tiền tại Anh và Mỹ. Có lẽ chính điều này mà người ta gọi Kim ngân xoắn là cây tiền. Lá cây Kim ngân xoắn xoè rộng như bàn tay, xanh quanh năm – một màu xanh tươi mát. Cây nở hoa từ tháng 4 đến tháng 11. Hoa gồm những cánh lớn màu kem nhạt, nở về đêm và toả hương thoang thoảng Đài hoa màu nâu nhạt hình bầu dục với 5 cánh màu xanh vàng, dài 15cm.", 320000, 1);
        SanPham e = new SanPham(5, "Cây Kim Ngân xoắn", "https://1.bp.blogspot.com/-fCutH05sQt0/XJCKcbpPQoI/AAAAAAAAFyE/pVBOukSE3w0znA3jp_qnxkP0zCg4xVU-ACLcBGAs/s1600/cay-kim-ngan-xoan.jpg", "Cây Kim Ngân xoắn có tên khoa học là Pachia aquatica, xuất xứ từ Mexico, Brazill Nam Mỹ và đầm lầy Trung Mỹ. Cây cao trên 6m, thân dẻo dai, bền chắc nên được dùng làm bột giấy in tiền tại Anh và Mỹ. Có lẽ chính điều này mà người ta gọi Kim ngân xoắn là cây tiền. Lá cây Kim ngân xoắn xoè rộng như bàn tay, xanh quanh năm – một màu xanh tươi mát. Cây nở hoa từ tháng 4 đến tháng 11. Hoa gồm những cánh lớn màu kem nhạt, nở về đêm và toả hương thoang thoảng Đài hoa màu nâu nhạt hình bầu dục với 5 cánh màu xanh vàng, dài 15cm.", 320000, 1);

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        return list;
    }

    public class AsynMHSP extends AsyncTask<Void, Void, List<SanPham>>{

        @Override
        protected List<SanPham> doInBackground(Void... voids) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getSanPhamByMenu(maMenu);
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            Log.d("size: ", String.valueOf(sanPhams.size()));
            for (int i = 0; i < sanPhams.size(); i++){
                arrayMHSP.add(sanPhams.get(i));

            }
            Log.d("size: ", String.valueOf(arrayMHSP.size()));
            manHinhSanPhamAdapter.notifyDataSetChanged();
        }
    }
}
