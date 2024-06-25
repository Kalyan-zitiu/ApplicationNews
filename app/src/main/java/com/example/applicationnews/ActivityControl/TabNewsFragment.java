package com.example.applicationnews.ActivityControl;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.applicationnews.R;
import com.example.applicationnews.adapter.NewsListAdapter;
import com.example.applicationnews.enity.NewsE;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// TabNewsFragment类继承自Fragment，用于在一个Tab中展示新闻
public class TabNewsFragment extends Fragment {

    private String url ="http://v.juhe.cn/toutiao/index?key=2d3e8b5a5f58b94cf0e859e6afb22b43&type=";
    // 根视图
    private View rootView;
    private RecyclerView recyclerView;
    // 适配器，用于将数据绑定到RecyclerView
    private NewsListAdapter mnewsListAdapter;

    // 用于从Bundle中获取标题参数的键
    private static final String ARG_PARAM = "tittle";
    private  String tittle;
    // 主线程的Handler，用于处理消息队列中的消息
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String data = (String) msg.obj;
                //使用Gson解析JSON数据
                NewsE newsE = new Gson().fromJson(data, NewsE.class);
                //如果新闻实体不为空且没有错误代码
                if (newsE != null && newsE.getErrorCode() == 0) {
                    //逻辑处理，更新适配器中的数据
                    if (null != mnewsListAdapter) {
                        mnewsListAdapter.setListData(newsE.getResult().getData());
                    }
                } else {
                    Toast.makeText(getActivity(), "获取数据失败，请重新尝试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public TabNewsFragment() {
        // Required empty public constructor
    }

    //采用静态工厂方法，用于创建TabNewsFragment实例
    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        //将标题参数放入Bundle
        args.putString(ARG_PARAM, param);
        //将Bundle设置为Fragment参数
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果参数Bundle不为空，取出标题参数
        if (getArguments() != null) {
            tittle = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);
        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化适配器
        mnewsListAdapter = new NewsListAdapter(getActivity());
        //设置adapter
        recyclerView.setAdapter(mnewsListAdapter);

        //recyclerView列表点击事件
        mnewsListAdapter.setMonItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsE.ResultDTO.DataDTO dataDTO, int position) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //传递对象的时候，该类一定要实现serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);
            }
        });

        //获取数据
        getHttpData();
    }
    //从API获取数据
    private void getHttpData(){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构构造Request对象
        Request request = new Request.Builder()
                //设置请求的URL
                .url(url + tittle)
                //设置请求方法为get
                .get()
                .build();
        //通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        //异步请求数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("------------------","onFailure: "+ e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Log.d("------------------","onResponse:" + data);
                Message message = new Message();
                message.what = 100;
                message.obj = data;
                //发送
                mHandler.sendMessage(message);


            }
        });

    }
}