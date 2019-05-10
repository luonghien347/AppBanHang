package com.th.thuhien.plantshop.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;

public class AdminMainActivity extends AppCompatActivity {

    DrawerLayout drawerLayoutQuanTri;
    NavigationView navigationViewQuanTri;
    Toolbar toolbarMainQuanTri;
    ImageButton btn_AdminMenu, btn_AdminSanPham;
    TextView txt_tenAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        AnhXa();
        NhanUsernameDangNhap();
        ActionBar();
        NavigationItemClick();
        EventButtonMenu();
        EventButtonSanPham();
    }

    private void EventButtonSanPham() {
        btn_AdminSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminSanPhamActivity.class);
                startActivity(intent);
            }
        });
    }

    private void EventButtonMenu() {
        btn_AdminMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void NavigationItemClick() {
        navigationViewQuanTri.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()){
                    case R.id.quantri_menu:
                        Intent intent = new Intent(AdminMainActivity.this, AdminMenuActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.quantri_sanpham:
                        Intent intent1 = new Intent(AdminMainActivity.this, AdminSanPhamActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.quantri_dondathang:
                        // gọi màn hình chuyển màn hình sang quản trị đơn đặt hàng
                        break;
                    case R.id.quantri_ctddh:
                        // gọi màn hình chuyển màn hình sang quản trị ctddh
                        break;
                }
                drawerLayoutQuanTri.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbarMainQuanTri);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMainQuanTri.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMainQuanTri.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutQuanTri.openDrawer(GravityCompat.START);

            }
        });
    }

    private void NhanUsernameDangNhap() {
        Intent intent = getIntent();
        txt_tenAdmin.setText(intent.getStringExtra("username"));

    }

    private void AnhXa() {
        drawerLayoutQuanTri = (DrawerLayout) findViewById(R.id.drawerLayoutQuanTri);
        navigationViewQuanTri = (NavigationView) findViewById(R.id.navigationViewQuanTri);
        toolbarMainQuanTri = (Toolbar) findViewById(R.id.toolbarMainQuanTri);

        View headView = navigationViewQuanTri.getHeaderView(0);
        txt_tenAdmin = (TextView) headView.findViewById(R.id.textviewTenUsser);

        btn_AdminMenu = (ImageButton) findViewById(R.id.imgbuttonAdminMenu);
        btn_AdminSanPham = (ImageButton) findViewById(R.id.imgbuttonAdminSanPham);

    }
}
