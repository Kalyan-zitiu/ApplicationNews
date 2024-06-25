package com.example.applicationnews.ActivityControl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.applicationnews.R;
import com.example.applicationnews.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText etusername;
    private EditText etpassword;

    private EditText containpassword;

//    private SharedPreferences msharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        获取msharedPreferences
//
//        msharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        //初始化控件
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        containpassword =findViewById(R.id.querenpassword);
        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //消费注册页面,注意不是跳转，跳转会新开一个登录页面
                finish();
            }
        });

        //点击注册
        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                String qcontainpassword = containpassword.getText().toString();
               if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(qcontainpassword)){
                   Toast.makeText(RegisterActivity.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(qcontainpassword)){
                   Toast.makeText(RegisterActivity.this,"前后密码不一致",Toast.LENGTH_SHORT).show();
               }else {
//                   SharedPreferences.Editor edit = msharedPreferences.edit();
//                   edit.putString("username",username);
//                   edit.putString("password",password);
                   int row =  UserDbHelper.getInstance(RegisterActivity.this).register(username,password,"暂无");
                   if (row>0) {
                       Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                       finish();
                   }
               }
            }
        });

    }
}