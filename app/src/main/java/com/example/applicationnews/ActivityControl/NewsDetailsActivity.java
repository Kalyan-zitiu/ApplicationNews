package com.example.applicationnews.ActivityControl;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationnews.R;
import com.example.applicationnews.db.CollectDbHelper;
import com.example.applicationnews.db.HistoryDbHelper;
import com.example.applicationnews.enity.NewsE;
import com.example.applicationnews.enity.UserE;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;


public class NewsDetailsActivity extends AppCompatActivity {

    private NewsE.ResultDTO.DataDTO dataDTO;
    private Toolbar toolbar;
    private WebView webView;
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        //初始化控件
        toolbar = findViewById(R.id.toolbar);
        webView =findViewById(R.id.webView);
        fab = findViewById(R.id.fab);

        //获取传递的数据
        dataDTO = (NewsE.ResultDTO.DataDTO)getIntent().getSerializableExtra("dataDTO");
        //设置数据
        if(null !=dataDTO){
            toolbar.setTitle(dataDTO.getTitle());
            webView.loadUrl(dataDTO.getUrl());

            //添加历史记录
            String dataDTOJson = new Gson().toJson(dataDTO);
            //获取用户名
            UserE userE = UserE.getsUserE();

            if (null!=userE){
                HistoryDbHelper.getInstance(NewsDetailsActivity.this).addHistory(userE.getUsername(),dataDTO.getUniquekey(),dataDTOJson);
            }else {
                HistoryDbHelper.getInstance(NewsDetailsActivity.this).addHistory(null,dataDTO.getUniquekey(),dataDTOJson);
            }
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataDTOJson = new Gson().toJson(dataDTO);
                UserE userE = UserE.getsUserE();
                if (null!=userE){
                    CollectDbHelper.getInstance(NewsDetailsActivity.this).addCollect(userE.getUsername(),dataDTO.getUniquekey(),dataDTOJson);
                }else {
                    CollectDbHelper.getInstance(NewsDetailsActivity.this).addCollect(null,dataDTO.getUniquekey(),dataDTOJson);
                }
            }
        });

        //返回
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
