package com.example.appshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editUser, editpassword;
    Button btnLogin, btnRegister, btnexit;
    String name,password;
    private List<Account> accounts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUser = findViewById(R.id.editTextUser);
        editpassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.btnregister);
        btnLogin = findViewById(R.id.btnlogin);
        btnexit = findViewById(R.id.btnexit);
        controlButton();
    }

    private void controlButton() {
        btnexit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Bạn có muốn thoát không");
            builder.setMessage("Lựa chọn của bạn");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Có", (dialog, which) -> onBackPressed());

            builder.setNegativeButton("Không ", (dialog, which) -> {

            });
            builder.show();
        });
        btnRegister.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.customdialog);
            EditText editregisterName = dialog.findViewById(R.id.registername);
            EditText editregisterPassword = dialog.findViewById(R.id.registerPassword);
            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            Button btnOk = dialog.findViewById(R.id.btnOK);
            btnOk.setOnClickListener(v1 -> {
                name = editregisterName.getText().toString().trim();
                password = editregisterPassword.getText().toString().trim();
                if(!name.isEmpty() && !password.isEmpty()){
                    Account account = new Account(name,password);
                    if(accounts.contains(account)){
                        System.out.println(accounts);
                        Toast.makeText(MainActivity.this,"Tên người dùng đã tồn tại"
                                , Toast.LENGTH_SHORT).show();
                    }else {
                        accounts.add(account);
                        editUser.setText(name);
                        editpassword.setText(password);
                        dialog.cancel();
                        Toast.makeText(MainActivity.this,"Đăng ký thành công"
                                ,Toast.LENGTH_SHORT).show();
                    }
                }


            });
            btnCancel.setOnClickListener(v12 -> dialog.cancel());
            dialog.show();
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    name = editUser.getText().toString().trim();
                    password = editpassword.getText().toString().trim();
                    Account account = new Account(name,password);
                    if (account != null && accounts.contains(account)){
                        Toast.makeText(MainActivity.this,"Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }

                }

        });
    }
}