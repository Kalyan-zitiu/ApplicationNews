package com.example.applicationnews.ActivityControl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationnews.R;
import com.example.applicationnews.adapter.NewsListAdapter;
import com.example.applicationnews.db.CollectDbHelper;

import com.example.applicationnews.enity.CollectE;

import com.example.applicationnews.enity.NewsE;
import com.example.applicationnews.enity.UserE;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CollectList extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private NewsListAdapter newsListAdapter;

    List<NewsE.ResultDTO.DataDTO> dataDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_list);
        //初始化控件
        recyclerView =findViewById(R.id.recyclerView);
        //初始化适配器
        newsListAdapter = new NewsListAdapter(this);
        //设置适配器
        recyclerView.setAdapter(newsListAdapter);
        UserE userE = UserE.getsUserE();

        String username = (userE!=null)?userE.getUsername():null;

        List<CollectE> CollectEList = CollectDbHelper.getInstance(CollectList.this).queryCollectListData(username);

        Gson gson = new Gson();

        for (int i = 0; i < CollectEList.size(); i++) {
            dataDTOList.add(gson.fromJson(CollectEList.get(i).getNew_json(),NewsE.ResultDTO.DataDTO.class));

        }
        //设置数据
        newsListAdapter.setListData(dataDTOList);

        //rectckerView点击事件
        newsListAdapter.setMonItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsE.ResultDTO.DataDTO dataDTO, int position) {
                //跳转到详情页面
                Intent intent = new Intent(CollectList.this,NewsDetailsActivity.class);
                //传递对象
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);
            }
        });
        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}