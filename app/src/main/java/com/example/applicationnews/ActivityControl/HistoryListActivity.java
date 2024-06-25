package com.example.applicationnews.ActivityControl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationnews.R;
import com.example.applicationnews.adapter.NewsListAdapter;
import com.example.applicationnews.db.HistoryDbHelper;
import com.example.applicationnews.enity.HistoryE;
import com.example.applicationnews.enity.NewsE;
import com.example.applicationnews.enity.UserE;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private NewsListAdapter newsListAdapter;

    List<NewsE.ResultDTO.DataDTO> dataDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        //初始化控件
        recyclerView =findViewById(R.id.recyclerView);
        //初始化适配器
        newsListAdapter = new NewsListAdapter(this);
        //设置适配器
        recyclerView.setAdapter(newsListAdapter);

        //获取数据之前先获取用户名字，确保只用来搜寻本用户账号内的历史记录

        UserE userE = UserE.getsUserE();
        //获取数据

        /**这样写的话会导致后面需要考虑空指针问题，所以需要稍微优化一下
         * 因为分开登录模式和游客模式，所以这里的设计是需要null的存储
         */
//        if (null!=userE){
//            List<HistoryE> historyEList =  HistoryDbHelper.getInstance(HistoryListActivity.this).queryHistoryListData(userE.getUsername());
//        }else {
//            List<HistoryE> historyEList =  HistoryDbHelper.getInstance(HistoryListActivity.this).queryHistoryListData(null);
//        }

        //避开后面一系列的空指针问题
        String username = (userE!=null)?userE.getUsername():null;
        List<HistoryE> historyEList =  HistoryDbHelper.getInstance(HistoryListActivity.this).queryHistoryListData(username);

        Gson gson = new Gson();

        for (int i = 0; i < historyEList.size(); i++) {
            dataDTOList.add(gson.fromJson(historyEList.get(i).getNew_json(),NewsE.ResultDTO.DataDTO.class));

        }
        //设置数据
        newsListAdapter.setListData(dataDTOList);

        //rectckerView点击事件
        newsListAdapter.setMonItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsE.ResultDTO.DataDTO dataDTO, int position) {
                //跳转到详情页面
                Intent intent = new Intent(HistoryListActivity.this,NewsDetailsActivity.class);
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