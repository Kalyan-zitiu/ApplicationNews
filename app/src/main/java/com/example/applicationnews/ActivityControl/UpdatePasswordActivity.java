package com.example.applicationnews.ActivityControl;

import android.content.Intent;
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
import com.example.applicationnews.enity.UserE;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText et_new_password;
    private EditText et_confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        //初始化控件
        et_new_password= findViewById(R.id.et_new_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);

        //修改密码点击事件
        findViewById(R.id.btn_update_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_pwd = et_new_password.getText().toString();
                String confirm_pwd = et_confirm_password.getText().toString();
                if (TextUtils.isEmpty(new_pwd)||TextUtils.isEmpty(confirm_pwd)){
                    Toast.makeText(UpdatePasswordActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                }else if (!new_pwd.equals(confirm_pwd)){
                    Toast.makeText(UpdatePasswordActivity.this, "前后密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    UserE userE = UserE.getsUserE();
                    if (null != userE){
                        int row = UserDbHelper.getInstance(UpdatePasswordActivity.this).updatePwd(userE.getUsername(),new_pwd);
                        if (row>0){
                            Toast.makeText(UpdatePasswordActivity.this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdatePasswordActivity.this,LoginActivity.class));
                            UserE.setsUserE(null);
                            finish();
                        }else {
                            Toast.makeText(UpdatePasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}