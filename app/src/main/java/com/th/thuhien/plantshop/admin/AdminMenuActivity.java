package com.th.thuhien.plantshop.admin;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.ultil.MenuService;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuActivity extends AppCompatActivity {

    Toolbar toolbarAdminMenu;
    ListView lv_AdminMenu;

    ArrayList<Menu> data;
    MenuAdapter menuAdapter;

    Menu select_menu;
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        AnhXa();
        ActionBar();
        setEventItemListview();
    }


    private void AnhXa() {
        toolbarAdminMenu = (Toolbar) findViewById(R.id.toolbarAdminMenu);
        lv_AdminMenu = (ListView) findViewById(R.id.listviewAdminMenu);

        data = new ArrayList<>();
        menuAdapter = new MenuAdapter(data, getApplicationContext());
        lv_AdminMenu.setAdapter(menuAdapter);

        registerForContextMenu(lv_AdminMenu);

        AsynAdminListMenu asynAdminListMenu = new AsynAdminListMenu();
        asynAdminListMenu.execute();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarAdminMenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdminMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setEventItemListview() {
        lv_AdminMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                select_menu = data.get(position);
                index = position;
                return false;
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarAdminMenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdminMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_admin_menu, menu);
        menu.getItem(0).setTitle("Sửa menu " + select_menu.getTenMenu());
        menu.getItem(1).setTitle("Xóa menu " + select_menu.getTenMenu());
        menu.getItem(2).setTitle("Thêm menu " + select_menu.getTenMenu());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.admin_menu_suaMenu:
                SuaMenu();
                break;
            case R.id.admin_menu_xoaMenu:
                XoaMenu();
                break;
            case R.id.admin_menu_themMenu:
                ThemMenu();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void SuaMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa menu");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        input.setText(select_menu.getTenMenu());
        input.setSelection(select_menu.getTenMenu().length());
        // Set up the buttons
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                Log.d("m_Text: ", m_Text);

                if (m_Text.equals("")){
                    Toast.makeText(getApplicationContext(), "Tên menu rỗng => Sửa thất bại", Toast.LENGTH_LONG).show();
                    return;
                }

                AsynUpdateMenu asynUpdateMenu = new AsynUpdateMenu();
                asynUpdateMenu.execute(String.valueOf(select_menu.getMaMenu()),m_Text);

                // load lại listview
                data.clear();
                AsynAdminListMenu asynAdminListMenu = new AsynAdminListMenu();
                asynAdminListMenu.execute();
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void ThemMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm menu");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                Log.d("m_Text: ", m_Text);
                if (m_Text.equals("")){
                    Toast.makeText(getApplicationContext(), "Phải nhập tên menu. Thêm thất bại", Toast.LENGTH_LONG).show();
                    return;
                }
                AsynInsertMenu asynInsertMenu = new AsynInsertMenu();
                asynInsertMenu.execute(m_Text);

                // load lại listview
                data.clear();
                AsynAdminListMenu asynAdminListMenu = new AsynAdminListMenu();
                asynAdminListMenu.execute();


            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void XoaMenu() {
        AsynDeleteMenu asynDeleteMenu = new AsynDeleteMenu();
        asynDeleteMenu.execute(data.get(index).getMaMenu());
    }

    // Asyn cho Menu -> Hien
    public class AsynAdminListMenu extends AsyncTask<Void, Void, List<Menu>>{

        @Override
        protected List<com.th.thuhien.plantshop.model.Menu> doInBackground(Void... voids) {
            MenuService menuService = new MenuService();
            return menuService.getListMenu();
        }

        @Override
        protected void onPostExecute(List<Menu> menus) {
            super.onPostExecute(menus);
            for (int i = 0; i < menus.size(); i++){
                data.add(menus.get(i));
            }
            menuAdapter.notifyDataSetChanged();
            lv_AdminMenu.setSelection(menuAdapter.getCount()-1);
        }
    }

    public class AsynInsertMenu extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            MenuService menuService = new MenuService();
            return menuService.insetMenu(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            Log.d("trave: ", String.valueOf(aBoolean));

        }
    }

    public class AsynUpdateMenu extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            MenuService menuService = new MenuService();
            return menuService.updateMenu(strings[0], strings[1]);
        }

    }


    public class AsynDeleteMenu extends AsyncTask<Integer, Void, Boolean>{


        @Override
        protected Boolean doInBackground(Integer... integers) {
            MenuService menuService = new MenuService();
            return menuService.deleteMenu(integers[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.d("boolean: ", String.valueOf(aBoolean));
            if (aBoolean == true){
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                data.clear();
                AsynAdminListMenu asynAdminListMenu = new AsynAdminListMenu();
                asynAdminListMenu.execute();
            }else {
                Toast.makeText(getApplicationContext(), "Không thành công", Toast.LENGTH_LONG).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setTitle("Xóa thất bại!");
//                builder.setMessage("Menu bạn muốn xóa đã chứa sản phẩm. Không thể xóa được!");
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        }
    }
}
