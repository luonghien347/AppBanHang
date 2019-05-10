package com.th.thuhien.plantshop.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.adapter.SanPhamAdapter;
import com.th.thuhien.plantshop.admin.AdminMainActivity;
import com.th.thuhien.plantshop.model.GioHang;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.DangNhapService;
import com.th.thuhien.plantshop.ultil.MenuService;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai báo
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    //ListView lv_SanPhamMoi;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewManhinhchinh;
    DrawerLayout drawerLayout;
    TextView txt_XemThemSPMoi;

    // Khai báo cho phần Menu
    ArrayList<Menu> arrayListMenu = new ArrayList<Menu>();
    MenuAdapter menuAdapter;

    // Khai báo cho phần SanPham mới
    ArrayList<SanPham> mangSanPham;
    SanPhamAdapter sanPhamAdapter;

    // Khai báo biến
    String user = "";


    //String tenMenu = "";
    private static final int GIOHANGCODE = 123;

    public static ArrayList<GioHang> arrayGioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionBar();
        ActionViewFlipper();
        ClickItemMenu();
        XemThemSPMoi();

    }

    private void XemThemSPMoi() {
        txt_XemThemSPMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SapXepSanPhamActivity.class);
                startActivity(intent);
            }
        });
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

    //Điều khiển menu: click item menu -> Hiền
    private void ClickItemMenu() {
        listViewManhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (position == arrayListMenu.size()-1){

                    DialogDangNhap();

//                    Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
//                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else
                {
                    Intent intent = new Intent(MainActivity.this, ManHinhSanPhamActivity.class);
                    intent.putExtra("maMenu", arrayListMenu.get(position).getMaMenu());
                    intent.putExtra("tenmenu", arrayListMenu.get(position).getTenMenu());
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    public void DialogDangNhap(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_dangnhap);
        //dialog.show();

        //Anh xa
        final EditText edt_user_dn = (EditText) dialog.findViewById(R.id.edittextDNUser);
        final EditText edt_pass_dn = (EditText) dialog.findViewById(R.id.edittextDNPass);
        Button btn_DongY = (Button) dialog.findViewById(R.id.buttonDNDongY);
        Button btn_Huy = (Button) dialog.findViewById(R.id.buttonDNHuy);

        btn_DongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_user_dn.getText().toString().trim();
                String matkhau = edt_pass_dn.getText().toString().trim();

                if (ten.equals("")){
                    Toast.makeText(MainActivity.this, "Tên đăng nhập không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }

                if (matkhau.equals("")){
                    Toast.makeText(MainActivity.this, "Mật không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }

                user = ten;

                AsynDangNhap asynDangNhap = new AsynDangNhap();
                asynDangNhap.execute(ten, matkhau);

                dialog.cancel();
            }
        });

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    // ViewFlipper banner cho app -> Hiền
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://1.bp.blogspot.com/-xA1DQYrcADo/XJcgoCV5NpI/AAAAAAAAF3k/f8UlgDDuYtMZzF3kF7-1JY7ITExYSgLzgCLcBGAs/s1600/banner-23.png");
        mangquangcao.add("https://4.bp.blogspot.com/-oBLvbX7wo1I/XJcgofGR5iI/AAAAAAAAF3o/Mih1AppGVrkz55R59Nis9KaAm5BZoEHWQCLcBGAs/s1600/banner-the-gioi-cay-va-hoa-961x343.png");
        mangquangcao.add("https://2.bp.blogspot.com/-sGgiHw_oGVo/XJcgoOP-VEI/AAAAAAAAF3g/nzkCfma8K_8QyRzTbYiW5IMaWYjBJcMpwCLcBGAs/s1600/banner_collection_master.jpg");
        mangquangcao.add("https://1.bp.blogspot.com/-6VB-i_N81fc/XJcgooEER7I/AAAAAAAAF3s/qb7a1FGtvysv2yXpnWlgwBNSgYB4tCL5wCLcBGAs/s1600/shop-cay-canh-tai-da-nang-10.jpg");

        for (int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        Animation animation_slide_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in_right);
        viewFlipper.setInAnimation(animation_slide_out_right);
    }

    // Điều khiển actionbar, set icon menu, mở khi click -> Hiền
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    private void AnhXa() {

        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        //lv_SanPhamMoi = findViewById(R.id.listViewSanPhamMoi);
        navigationView = findViewById(R.id.navigationView);
        listViewManhinhchinh = findViewById(R.id.listViewManhinhchinh);
        drawerLayout = findViewById(R.id.drawerLayout);
        txt_XemThemSPMoi = (TextView) findViewById(R.id.textviewXemThemSPMoi);

        // Menu -> Hiền
        menuAdapter = new MenuAdapter(arrayListMenu, getApplicationContext());
        listViewManhinhchinh.setAdapter(menuAdapter);

        arrayListMenu.add(new Menu(0, "Trang chủ"));

        AsynListMenu asynListMenu = new AsynListMenu();
        asynListMenu.execute();
        // -- Ket thuc Menu

        // SanPham moi -> HIền
        mangSanPham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), R.layout.dong_sanpham_moinhat,mangSanPham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewmanhinhchinh.setLayoutManager(linearLayoutManager);
        //recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanPhamAdapter);

        AsysListSanPhamMoi asysListSanPhamMoi = new AsysListSanPhamMoi();
        asysListSanPhamMoi.execute(6);

        // --Ket thuc SanPham moi

        if (arrayGioHang != null){


        }else {
            arrayGioHang = new ArrayList<>();
        }
    }


    // Asyn cho Menu -> Hien
    public class AsynListMenu extends AsyncTask<Void, Void, List<Menu>>{


        @Override
        protected List<Menu> doInBackground(Void... voids) {
            MenuService menuService = new MenuService();
            return menuService.getListMenu();
        }

        @Override
        protected void onPostExecute(List<Menu> menu) {
            super.onPostExecute(menu);
            for (int i = 0; i < menu.size(); i++){
                arrayListMenu.add(menu.get(i));
            }
            arrayListMenu.add(new Menu("Quản trị"));
            menuAdapter.notifyDataSetChanged();
        }
    }

    // Aysn cho SanPhamMoi -> Hien
    public class AsysListSanPhamMoi extends AsyncTask<Integer, Void, List<SanPham>>{

        @Override
        protected List<SanPham> doInBackground(Integer... integers) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getListSpMoi(integers[0]);
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            for (int i = 0; i < sanPhams.size(); i++){
                mangSanPham.add(sanPhams.get(i));
            }
            sanPhamAdapter.notifyDataSetChanged();
        }
    }

    public class AsynDangNhap extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            DangNhapService dangNhapService = new DangNhapService();
            return dangNhapService.kiemTraDangNhap(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer == 1){
                //Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}
