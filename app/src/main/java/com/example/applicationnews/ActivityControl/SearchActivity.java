package com.example.applicationnews.ActivityControl;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationnews.R;
import com.example.applicationnews.adapter.NewsListAdapter;
import com.example.applicationnews.enity.NewsE;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private NewsListAdapter newsListAdapter;


    List<NewsE.ResultDTO.DataDTO> dataDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //初始化控件
        recyclerView = findViewById(R.id.recyclerView);

        //初始化适配器
        newsListAdapter = new NewsListAdapter(this);

        //设置适配器
        recyclerView.setAdapter(newsListAdapter);

        /**
         * 这里并不需要获取用户名字，不像history那里，这里只需要通过tittle来寻找大概的新闻就可以了
         * 所以这里只需要获取新闻中的tittle就好
         * 问题在于历史列表那里的数据是通过sqllite来进行数据库搜寻的，那么这里我们该怎么获取title数据呢
         */




        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}