package com.example.applicationnews.ActivityControl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.applicationnews.MainActivity;
import com.example.applicationnews.R;
import com.example.applicationnews.db.UserDbHelper;
import com.example.applicationnews.enity.UserE;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;

    private  boolean is_login;

    private CheckBox checkbox;

    private SharedPreferences msharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        et_username = findViewById(R.id.et_username);
        et_password= findViewById(R.id.et_password);
        checkbox= findViewById(R.id.checkbox);

        //获取msharedPreferences

        msharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        //是否勾选了密码
        is_login = msharedPreferences.getBoolean("is_login",false);
        if(is_login){
            String name = msharedPreferences.getString("username",null);
            String pwd = msharedPreferences.getString("password",null);
            et_username.setText(name);
            et_password.setText(pwd);
            checkbox.setChecked(true);
        }

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String password =et_password.getText().toString();
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{

                    UserE login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if (login !=null){
                        if (username.equals(login.getUsername()) && password.equals(login.getPassword())){
                            //保存是否记住密码
                            SharedPreferences.Editor edit = msharedPreferences.edit();
                            edit.putBoolean("is_login",is_login);
                            edit.putString("username",username);
                            edit.putString("password",password);
                            edit.commit();
                            UserE.setsUserE(login);

                            //登录成功
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "账号未注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //checkbox的点击事件
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                is_login =b;

            }
        });

    }
}