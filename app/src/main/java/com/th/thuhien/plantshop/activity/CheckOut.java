package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.admin.AdminMainActivity;
import com.th.thuhien.plantshop.ultil.DDH;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckOut extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editDiachi;
    Button btnBack, btnDathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        setControl();
        setEvent();
    }

    private void setEvent() {

        int dem = 0;

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editName.getText().toString().length() <= 0) {
                    editName.setError("Enter Name");
                } else {
                    editName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editName.getText().toString().length() <= 0) {
                    editName.setError("Enter Name");
                } else {
                    editName.setError(null);
                }
            }
        });

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editEmail.getText().toString().length() <= 0) {
                    editEmail.setError("Enter PhoneNumber");
                } else {
                    editEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editPhone.getText().toString().length() <= 0) {
                    editPhone.setError("Enter PhoneNumber");
                } else {
                    editPhone.setError(null);
                }
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editEmail.getText().toString().length() <= 0) {
                    editEmail.setError("Enter Email");
                } else {
                    editEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editEmail.getText().toString().length() <= 0) {
                    editEmail.setError("Enter Email");
                } else {
                    editEmail.setError(null);
                }
            }
        });

        editDiachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editEmail.getText().toString().length() <= 0) {
                    editEmail.setError("Enter Address");
                } else {
                    editEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editDiachi.getText().toString().length() <= 0) {
                    editDiachi.setError("Enter Address");
                } else {
                    editDiachi.setError(null);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editName.getText().toString().trim();
                String email = editEmail.getText().toString();
                String sdt = editPhone.getText().toString();
                String diachi = editDiachi.getText().toString();

                if (ten.equals("")){
                    Toast.makeText(getApplicationContext(), "Không được để trống tên!", Toast.LENGTH_SHORT).show();
                }

                else if (sdt.equals("")){
                    Toast.makeText(getApplicationContext(), "Không được để trống số điện thoại!", Toast.LENGTH_SHORT).show();
                }

                else if (diachi.equals("")){
                    Toast.makeText(getApplicationContext(), "Không được để trống địa chỉ!", Toast.LENGTH_SHORT).show();
                }

                else if (email.equals("")){
                    Toast.makeText(getApplicationContext(), "Không được để trống email!", Toast.LENGTH_SHORT).show();
                }
                else {
                    AsyncDDH asyncDDH = new AsyncDDH();
                    asyncDDH.execute(ten, sdt, email, diachi);
                    Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    MainActivity.arrayGioHang = null;
                    Intent intent = new Intent(CheckOut.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setControl() {
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editDiachi = (EditText) findViewById(R.id.editDiachi);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnDathang = (Button) findViewById(R.id.btnDathang);
    }

    public class AsyncDDH extends AsyncTask<String, Void, Integer>{

        int rs = 0;
        @Override
        protected Integer doInBackground(String... strings) {
            DDH createDDH = new DDH();
            return rs = createDDH.insertDDH(strings[0], strings[1], strings[2], strings[3]);
        }
    }

    private int checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (matcher.find()) {
            System.out.println("Email của bạn hợp lệ!");
            return 1;
        } else {
            System.out.println("Email của bạn chưa hợp lệ!");
            return 0;
        }
    }

}
